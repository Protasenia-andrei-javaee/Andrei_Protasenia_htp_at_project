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

public class BookingFavoriteJunitTest {
    private static final Logger LOGGER = LogManager.getLogger(BookingFavoriteJunitTest.class);
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";

    int daysTrip = 5;
    int daysAfterToday = 30;
    int adultsValue = 2;
    int childrenValue = 0;
    int roomsValue = 1;

    BookingPage bookingPageObject;
    Properties bookingProperties;


    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        bookingProperties = Driver.getProperties(BOOKING_PATH);
        bookingPageObject = new BookingPage(Driver.getWebDriver());
    }

    @Test
    public void favoritesTest() throws InterruptedException {
        bookingPageObject.signIn(bookingProperties);
        TimeUnit.SECONDS.sleep(3);
        bookingPageObject.setData("Minsk", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
        TimeUnit.SECONDS.sleep(5);
        bookingPageObject.setFavoriteHotel();
        bookingPageObject.checkHotelID(bookingPageObject.getFirstHotel(), bookingPageObject.getSecondHotel());
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}