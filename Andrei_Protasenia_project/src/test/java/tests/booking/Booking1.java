package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.BookingSteps;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Booking1 {
    int adults = 4;
    int days = 7;
    int daysFromNow = 3;
    int rooms = 2;
    static String BOOKING_URL = "https://www.booking.com/";
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BookingSteps.SetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }
    @Test
    public void booking1() throws InterruptedException {
        BookingSteps.findElementKeys(driver, "//*[@id='ss']", "Paris");
        BookingSteps.findElementEnter(driver, "//*[@data-mode='checkin']");
        BookingSteps.findElementEnter(driver, String.format("//*[@data-date='%s']", BookingSteps.setDays(daysFromNow)));
        BookingSteps.findElementEnter(driver, String.format("//*[@data-date='%s']", BookingSteps.setDays(daysFromNow + days)));
        BookingSteps.findElementEnter(driver, "//*[@id='xp__guests__toggle']");
        int adults = Integer.parseInt(BookingSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-adult')]//input", "value"));
        BookingSteps.findElementClickRepeat(driver, "//*[@aria-describedby='group_adults_desc'][2]", adults, this.adults);
        int rooms = Integer.parseInt(BookingSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-rooms')]//input", "value"));
        BookingSteps.findElementClickRepeat(driver, "//*[@aria-describedby='no_rooms_desc'][2]", rooms, this.rooms);
        BookingSteps.findElementEnter(driver, "(//*[contains(@type,'submit')])[1]");
        TimeUnit.SECONDS.sleep(3);
        BookingSteps.findElementEnter(driver, "//*[contains(@class,'sort_price')]");
        TimeUnit.SECONDS.sleep(3);
        BookingSteps.findElementEnter(driver, "//*[@data-id='pri-5']");
        TimeUnit.SECONDS.sleep(3);
        String pricePerNight = BookingSteps.findElementByText(driver, "//*[@data-id='pri-5']").replaceAll("\\D+", "");
        String minFromMax = BookingSteps.findElementByText(driver, "(//*[contains(@class,'bui-price-display')]/div[2]/div)[1]").replaceAll("\\D+", "");
        int hotelPerNight = Integer.parseInt(minFromMax) / days;
        System.out.println("Price per night from " + pricePerNight);
        System.out.println("Minimum price per night from " + hotelPerNight);
        assertTrue("Mistake", hotelPerNight >= Integer.parseInt(pricePerNight));
    }
    @After
    public void postCondition() {
        BookingSteps.destroy(driver);
    }
}