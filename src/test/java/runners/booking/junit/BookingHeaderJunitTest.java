package runners.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import settings.Config;
import webdriver.Driver;
import pages.BookingPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingHeaderJunitTest {
    private static final Logger LOGGER = LogManager.getLogger(BookingHeaderJunitTest.class);
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    static BookingPage bookingPageObject;
    static Properties properties;

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        bookingPageObject = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void headerTest() throws InterruptedException {
        LOGGER.info("Check headers");
        bookingPageObject.signIn(properties);
        TimeUnit.SECONDS.sleep(3);
        bookingPageObject.checkHeader();
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}