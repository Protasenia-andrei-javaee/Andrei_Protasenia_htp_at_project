package steps.cucumber.booking.bookingParis;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.Config;
import settings.ScreenMode;
import webdriver.Driver;
import pages.BookingPage;

import java.net.MalformedURLException;

public class BookingParis {
    private static final Logger LOGGER = LogManager.getLogger(BookingParis.class);
    BookingPage bookingPage;
    int daysTrip = 7;
    int daysAfterToday = 3;
    int adultsValue = 4;
    int childrenValue = 0;
    int roomsValue = 2;


    @Given("I go to booking.com")
    public void goToBooking() throws MalformedURLException {
        LOGGER.info("Start");
        Driver.initDriver(Config.CHROME);
        Driver.clearBrowser();
        bookingPage = new BookingPage(Driver.getWebDriver());
        Driver.setWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I set dropbox data")
    public void setDropBoxValues() throws InterruptedException {
        bookingPage.setData("Paris", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
    }

    @And("I sort hotels by max budget")
    public void sortHotelsByBudget() throws InterruptedException {
        bookingPage.sortForParis();
    }

    @And("I check price of hotel and price in filters")
    public void checkPrices() {
        bookingPage.checkParisHotels(daysTrip);
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}