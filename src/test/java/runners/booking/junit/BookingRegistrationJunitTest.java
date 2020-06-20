package runners.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import settings.Config;
import steps.trashmail.TrashMail_Steps;
import webdriver.Driver;
import pages.BookingPage;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingRegistrationJunitTest {
    private static final Logger LOGGER = LogManager.getLogger(BookingRegistrationJunitTest.class);

    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final String CURRENT_ACCOUNT = "//*[contains(@id,'current_account')]";
    private static final String MY_DASHBOARD = "//*[contains(@class,'mydashboard')]";
    private static final String CONTAINS_SENDER ="//*[contains(text(),'%s')]";
    private static final String SENDER = "booking.com";
    private static final String EMAIL_CONFIRM = "//*[contains(text(),'Подтверждаю')]";
    private static final String EMAIL_CHECKER = "//*[@class='email-confirm-banner']";
    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String YANDEX_URL = "https://mail.yandex.ru/";
    BookingPage bookingPageObject;
    Properties properties;

    @Before
    public void preCondition() throws IOException, InterruptedException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        TrashMail_Steps.trashGetNewMail();
        Driver.url(BOOKING_URL);
        bookingPageObject = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        LOGGER.debug("Create new user");
        bookingPageObject.registration(properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(5);
        LOGGER.debug("Go to mail.yandex.ru");
        Driver.getWebDriver().get(YANDEX_URL);
        TimeUnit.SECONDS.sleep(5);
        String currentHandle = Driver.getWebDriver().getWindowHandle();
        TimeUnit.SECONDS.sleep(2);
        if(!TrashMail_Steps.REGISTERED_STATUS){
            Driver.findElementClick(String.format(CONTAINS_SENDER, SENDER));
        }
        else {
            TrashMail_Steps.checkOnYandexMail(SENDER);
        }
        TimeUnit.SECONDS.sleep(5);
        Driver.findElementClick(EMAIL_CONFIRM);
        TimeUnit.SECONDS.sleep(8);
        Set<String> handles = Driver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                Driver.getWebDriver().switchTo().window(currentHandle);
            }
        }
        LOGGER.debug("Go to booking.com");
        Driver.getWebDriver().get(BOOKING_URL);
        TimeUnit.SECONDS.sleep(3);
        Driver.findElementClick(CURRENT_ACCOUNT);
        TimeUnit.SECONDS.sleep(3);
        Driver.findElementClick(MY_DASHBOARD);
        TimeUnit.SECONDS.sleep(3);
        LOGGER.debug("Check email");
        assertEquals(Driver.getWebDriver().findElements(By.xpath(EMAIL_CHECKER)).size(), 0);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}