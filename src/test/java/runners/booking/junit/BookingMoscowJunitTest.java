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

public class BookingMoscowJunitTest {
    private static final Logger LOGGER = LogManager.getLogger(BookingMoscowJunitTest.class);

    int daysTrip = 5;
    int daysAfterToday = 10;
    int adultsValue = 2;
    int childrenValue = 0;
    int roomsValue = 1;
    BookingPage bookingPageObject;
    Actions actions;


    @Before
    public void preCondition() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.clearBrowser();
        bookingPageObject = new BookingPage(Driver.getWebDriver());
        actions = new Actions(Driver.getWebDriver());
    }

    @Test
    public void moscowTest() throws InterruptedException {
        LOGGER.info("Set data for search hotel");
        Driver.setWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        bookingPageObject.setData("Moscow", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
        bookingPageObject.actionsForMoscow();
        bookingPageObject.sortMoscow();
        bookingPageObject.checkMoscowHotels(daysTrip);
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
    }
}