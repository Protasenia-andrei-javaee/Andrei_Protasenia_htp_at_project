package steps.trashmail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import webdriver.Driver;
import pages.TrashMailPage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashMail_Steps {
    public static boolean REGISTERED_STATUS = true;
    private static final Logger LOGGER = LogManager.getLogger(TrashMail_Steps.class);

    static final String NEW_EMAIL = "//*[@id='fe-mob-name']";
    static final String SIGN_IN = "//*[contains(@class,'HeadBanner-Button-Enter')]";
    static final String ENTER_BUTTON = "//*[contains(@class,'submit passp-form-button')]";
    static final String LOGIN_FIELD = "//*[@id='passp-field-login']";
    static final String PASSWORD_FIELD = "//*[@id='passp-field-passwd']";
    static final String SENDER = "//*[contains(text(),'%s')]";
    static final String BOOKING_PROPERTIES = "src/test/resources/properties/booking.properties";
    static final String TRASHMAIL_PROPERTIES = "src/test/resources/properties/trashMail.properties";
    static final String YANDEX_PROPERTIES = "src/test/resources/properties/yandexMail.properties";
    static final String REAL_EMAIL = "//*[@id='fe-mob-forward']";
    static final String CHECKER = "//*[contains(text(),'address is not registered')]";
    static final String TRASHMAIL_URL = "https://trashmail.com/";
    static final String YANDEX_URL = "https://mail.yandex.ru/";
    static final String MAIL_ATTRIBUTE = "value";
    static final String DOMAIN = "@trashmail.com";

    private static void getNewTrashMail() throws IOException {
        String newMail = Driver.getAttribute(NEW_EMAIL, MAIL_ATTRIBUTE);
        newMail = newMail.concat(DOMAIN);
        emailProperties(newMail, BOOKING_PROPERTIES);
    }
    public static void checkOnYandexMail(String sender) throws InterruptedException, IOException {
        LOGGER.debug("Going to yandex.ru");
        Driver.url(YANDEX_URL);
        Properties prop = Driver.getProperties(YANDEX_PROPERTIES);
        TimeUnit.SECONDS.sleep(2);
        Driver.findElementClick(SIGN_IN);
        Driver.sendKeys(LOGIN_FIELD, prop.getProperty("EMAIL"));
        Driver.findElementClick(ENTER_BUTTON);
        TimeUnit.SECONDS.sleep(2);
        Driver.sendKeys(PASSWORD_FIELD, prop.getProperty("PASSWORD"));
        Driver.findElementClick(ENTER_BUTTON);
        TimeUnit.SECONDS.sleep(5);
        Driver.findElementClick(String.format(SENDER, sender));
        TimeUnit.SECONDS.sleep(2);
    }

    public static void emailProperties(String newMail, String propertiesPath) throws IOException {
        LOGGER.debug("Writing trash email in properties");
        Properties prop = Driver.getProperties(propertiesPath);
        OutputStream out = new FileOutputStream(propertiesPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
    public static void trashGetNewMail() throws InterruptedException, IOException {
        LOGGER.debug("Registering new trash mail");
        Properties prop = Driver.getProperties(TRASHMAIL_PROPERTIES);
        Driver.getWebDriver().get(TRASHMAIL_URL);
        if (REGISTERED_STATUS)
            Driver.sendKeys(REAL_EMAIL, prop.getProperty("EMAIL"));
        getNewTrashMail();
        TrashMailPage trashMailPage = new TrashMailPage(Driver.getWebDriver());
        trashMailPage.generateMail();
        TimeUnit.SECONDS.sleep(2);
        if (Driver.getWebDriver().findElements(By.xpath(CHECKER)).size() > 0) {
            LOGGER.debug("Account not registered");
            REGISTERED_STATUS = false;
            trashMailPage.trashmailRegistration();
            trashGetNewMail();
        }
        TimeUnit.SECONDS.sleep(3);
    }
}