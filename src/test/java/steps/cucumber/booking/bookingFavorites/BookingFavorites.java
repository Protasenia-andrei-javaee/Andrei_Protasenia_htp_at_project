package steps.cucumber.booking.bookingFavorites;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.Config;
import settings.ScreenMode;
import webdriver.Driver;
import pages.BookingPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingFavorites {
    private static final Logger LOGGER = LogManager.getLogger(BookingFavorites.class);
    Properties properties;
    int daysTrip = 5;
    int daysAfterToday = 30;
    int adultsValue = 2;
    int childrenValue = 0;
    int roomsValue = 1;
    static BookingPage bookingPage;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";


    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Given("I go to booking.com")
    public void goToBooking() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.clearBrowser();
        bookingPage = new BookingPage(Driver.getWebDriver());
        Driver.setWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I sing in")
    public void signIn() throws InterruptedException {
        bookingPage.signIn(properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @And("I set dropbox values")
    public void setDropBoxValues() throws InterruptedException {
        bookingPage.setData("Madrid", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
        TimeUnit.SECONDS.sleep(5);
    }

    @And("I add two hotels to list and checking color")
    public void addTwoHotels() throws InterruptedException {
        bookingPage.setFavoriteHotel();
    }

    @And("I check list")
    public void checkList() throws InterruptedException {
        bookingPage.checkHotelID(bookingPage.getFirstHotel(), bookingPage.getSecondHotel());
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}