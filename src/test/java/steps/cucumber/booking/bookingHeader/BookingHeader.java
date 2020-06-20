package steps.cucumber.booking.bookingHeader;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import settings.Config;
import settings.ScreenMode;
import webdriver.Driver;
import pages.BookingPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingHeader {
    private static final Logger LOGGER = LogManager.getLogger(BookingHeader.class);
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    static BookingPage bookingPage;
    static Properties properties;
    static List<WebElement> list;

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        list = new ArrayList<>();
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Given("I go to booking.com")
    public void goToBooking() throws MalformedURLException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        Driver.clearBrowser();
        bookingPage = new BookingPage(Driver.getWebDriver());
        Driver.setWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I sign in")
    public void signIn() throws InterruptedException {
        bookingPage.signIn(properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @And("I check header elements")
    public void checkHeaders() {
        bookingPage.checkHeader();
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}