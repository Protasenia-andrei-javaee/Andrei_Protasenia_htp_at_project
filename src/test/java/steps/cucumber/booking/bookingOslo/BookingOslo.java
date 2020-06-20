package steps.cucumber.booking.bookingOslo;

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

public class BookingOslo {
    private static final Logger LOGGER = LogManager.getLogger(BookingOslo.class);
    BookingPage bookingPage;
    int daysTrip = 1;
    int daysAfterToday = 1;
    int adultsValue = 2;
    int roomsValue = 1;
    int childrenValue = 1;


    @Given("I go to booking.com")
    public void goToBooking() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.clearBrowser();
        bookingPage = new BookingPage(Driver.getWebDriver());
        Driver.setWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I set dropbox data")
    public void setDropBoxValues() throws InterruptedException {
        bookingPage.setData("Oslo", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
    }

    @And("I choose three-four star hotels")
    public void chooseHotelsByStars() throws InterruptedException {
        bookingPage.sortOslo();
    }

    @And("I set and check colors")
    public void setAndCheckColors() throws InterruptedException {
        bookingPage.actionsOslo();
        Driver.testDestroy();
    }
}