package steps.cucumber.booking.bookingRegistration;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import settings.Config;
import settings.ScreenMode;
import steps.trashmail.TrashMail_Steps;
import webdriver.Driver;
import pages.BookingPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingRegistration {
    private static final Logger LOGGER = LogManager.getLogger(BookingRegistration.class);
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String YANDEX_URL = "https://mail.yandex.ru/";
    private static final String CURRENT_ACCOUNT_LOCATOR = "//*[contains(@id,'current_account')]";
    private static final String DASHBOARD_LOCATOR = "//*[contains(@class,'mydashboard')]";
    private static final String CONTAINS_SENDER_LOCATOR = "//*[contains(text(),'%s')]";
    private static final String SENDER_LOCATOR = "booking.com";
    private static final String CONFIRM_LOCATOR = "//*[contains(text(),'Подтверждаю')]";
    private static final String CHECKER_LOCATOR = "//*[@class='email-confirm-banner']";
    BookingPage bookingPage;
    Properties properties;

    @Before
    public void preCondition() throws IOException, InterruptedException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        TrashMail_Steps.trashGetNewMail();
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Given("I go to booking.com")
    public void goToBooking() throws MalformedURLException {
        Driver.url(BOOKING_URL);
    }

    @Then("I register new user")
    public void createNewUser() throws InterruptedException, IOException {
        LOGGER.debug("I register new user");
        bookingPage.registration(properties, BOOKING_PATH);
        TimeUnit.SECONDS.sleep(5);
    }

    @And("I go to mail.yandex.by")
    public void goToYandex() throws InterruptedException, IOException {
        LOGGER.debug("I go to mail.yandex.by");
        Driver.getWebDriver().get(YANDEX_URL);
        TimeUnit.SECONDS.sleep(5);
        String currentHandle = Driver.getWebDriver().getWindowHandle();
        TimeUnit.SECONDS.sleep(3);
        if (!TrashMail_Steps.REGISTERED_STATUS) {
            Driver.findElementClick(String.format(CONTAINS_SENDER_LOCATOR, SENDER_LOCATOR));
        } else {
            TrashMail_Steps.checkOnYandexMail(SENDER_LOCATOR);
        }
        TimeUnit.SECONDS.sleep(5);
        Driver.findElementClick(CONFIRM_LOCATOR);
        TimeUnit.SECONDS.sleep(8);
        Set<String> handles = Driver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                Driver.getWebDriver().switchTo().window(currentHandle);
            }
        }
    }

    @Then("I go to booking.com second time")
    public void goToBookingSecondTime() throws InterruptedException {
        LOGGER.debug("I came to the booking.com second time");
        Driver.getWebDriver().get(BOOKING_URL);
        TimeUnit.SECONDS.sleep(4);
    }

    @And("I go to dashboard")
    public void goToDashBoard() throws InterruptedException {
        LOGGER.debug("Go to dashboard");
        Driver.findElementClick(CURRENT_ACCOUNT_LOCATOR);
        TimeUnit.SECONDS.sleep(4);
        Driver.findElementClick(DASHBOARD_LOCATOR);
        TimeUnit.SECONDS.sleep(4);
    }

    @And("I check banner")
    public void checkBanner() {
        LOGGER.debug("I check lack of banner");
        assertEquals(Driver.getWebDriver().findElements(By.xpath(CHECKER_LOCATOR)).size(), 0);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}