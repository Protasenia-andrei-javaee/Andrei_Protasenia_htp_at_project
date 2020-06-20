package runners.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import settings.Config;
import settings.ScreenMode;
import webdriver.Driver;
import pages.BookingPage;

import java.net.MalformedURLException;

public class BookingParisJunitTest {
    private static final Logger LOGGER = LogManager.getLogger(BookingParisJunitTest.class);

    int daysTrip = 7;
    int daysAfterToday = 3;
    int adultsValue = 4;
    int childrenValue = 0;
    int roomsValue = 2;
    BookingPage bookingPageObject;


    @Before
    public void preCondition() throws MalformedURLException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        Driver.clearBrowser();
        bookingPageObject = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void parisTest() throws InterruptedException {
        Driver.setWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        bookingPageObject.setData("Paris", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
        bookingPageObject.sortForParis();
        bookingPageObject.checkParisHotels(daysTrip);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
    }
}