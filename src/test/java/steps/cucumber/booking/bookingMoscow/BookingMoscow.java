package steps.cucumber.booking.bookingMoscow;

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

public class BookingMoscow {
    private static final Logger LOGGER = LogManager.getLogger(BookingMoscow.class);
    BookingPage bookingPage;
    int daysTrip = 5;
    int daysAfterToday = 10;
    int adultsValue = 2;
    int childrenValue = 0;
    int roomsValue = 1;


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
        bookingPage.setData("Moscow", daysTrip, daysAfterToday, adultsValue, childrenValue, roomsValue);
    }

    @Then("I do actions with data")
    public void dataActions() throws InterruptedException {
        bookingPage.actionsForMoscow();
    }

    @And("I choose hotels from min budget")
    public void chooseMinBudgetHotel() throws InterruptedException {
        bookingPage.sortMoscow();
    }

    @And("I check price of hotel and price in filters")
    public void checkPrices() throws InterruptedException {
        bookingPage.checkMoscowHotels(daysTrip);
        LOGGER.info("Finish");
        Driver.testDestroy();
    }
}