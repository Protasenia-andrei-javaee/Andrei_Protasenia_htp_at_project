package steps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailSteps {
    private static final Logger LOGGER = LogManager.getLogger(MailSteps.class);

    public static void confirmYandexMail(String sender) throws InterruptedException, IOException {
        LOGGER.debug("Going to yandex.ru");
        MyDriver.goToSite("https://mail.yandex.ru/");
        Properties prop = MyDriver.getProperties(PropertyPath.YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        MyDriver.ElementClick("//*[contains(@class, \"HeadBanner-Button-Enter\")]");
        LOGGER.debug("Printing email");
        MyDriver.findElementSendKeys("//*[@id= \"passp-field-login\"]", prop.getProperty("EMAIL"));
        MyDriver.ElementClick("//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(2);
        LOGGER.debug("Printing password");
        MyDriver.findElementSendKeys("//*[@id= \"passp-field-passwd\"]", prop.getProperty("PASSWORD"));
        MyDriver.ElementClick("//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(5);
        LOGGER.debug("Finding email from " + sender);
        MyDriver.ElementClick(String.format("//*[contains(text(), \"%s\")]", sender));
        TimeUnit.SECONDS.sleep(2);
    }

    public static void putEmail(String newMail, String propertyPath) throws IOException {
        LOGGER.debug("Put new trash email in property");
        Properties prop = MyDriver.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}
