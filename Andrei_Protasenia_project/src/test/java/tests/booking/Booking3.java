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

public class Booking3 {
    int daysFromNow = 1;
    int days = 1;
    int children = 1;
    static String BOOKING_URL="https://www.booking.com/";
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BookingSteps.SetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }

    @Test
    public void booking3() throws InterruptedException {
        BookingSteps.findElementKeys(driver, "//*[@id='ss']", "Oslo");
        BookingSteps.findElementEnter(driver, "//*[@data-mode='checkin']");
        BookingSteps.findElementEnter(driver, String.format("//*[@data-date='%s']", BookingSteps.setDays(daysFromNow)));
        BookingSteps.findElementEnter(driver, String.format("//*[@data-date='%s']", BookingSteps.setDays(daysFromNow + days)));
        BookingSteps.findElementEnter(driver, "//*[@id='xp__guests__toggle']");
        int children = Integer.parseInt(BookingSteps.findElementGetAttribute(driver, "//*[contains(@class,'children')]//input", "value"));
        BookingSteps.findElementClickRepeat(driver, "//*[@aria-describedby='group_children_desc'][2]", children, this.children);
        BookingSteps.findElementEnter(driver, "(//*[contains(@type,'submit')])[1]");
        TimeUnit.SECONDS.sleep(3);
        BookingSteps.findElementEnter(driver, "//*[@data-id='class-3']");
        BookingSteps.findElementEnter(driver, "//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(3);
        BookingSteps.findElementScroll(driver, "//*[@data-hotelid][10]");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        BookingSteps.findElementHighlight(driver, "//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        BookingSteps.findElementSetAttribute(driver, "//*[@data-hotelid][10]", "backgroundColor", "green");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        BookingSteps.findElementSetAttribute(driver, "//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]", "color", "red");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        BookingSteps.findElementCheckAttribute(driver, "//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]", "style", "color: red;");
    }
    @After
    public void postCondition() {
        BookingSteps.destroy(driver);
    }
}