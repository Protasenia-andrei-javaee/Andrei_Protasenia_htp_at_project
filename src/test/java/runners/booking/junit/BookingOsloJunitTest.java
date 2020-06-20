package runners.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import webdriver.Driver;
import pages.BookingPage;

import java.net.MalformedURLException;

public class BookingOsloJunitTest {
    private static final Logger LOGGER = LogManager.getLogger(BookingOsloJunitTest.class);

    int daysTrip = 1;
    int daysAfterToday = 1;
    int adultsValue = 2;
    int roomsValue = 1;
    int childrenValue = 1;
    BookingPage bookingPageObject;
    Actions actions;

    @Before
    public void preCondition() throws MalformedURLException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        Driver.clearBrowser();
        bookingPageObject = new BookingPage(Driver.getWebDriver());
        actions = new Actions(Driver.getWebDriver());
    }

    @Test
    public void osloTest() throws InterruptedException {
        Driver.setWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        bookingPageObject.setData("Oslo", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
        bookingPageObject.sortOslo();
        bookingPageObject.actionsOslo();
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
    }
}