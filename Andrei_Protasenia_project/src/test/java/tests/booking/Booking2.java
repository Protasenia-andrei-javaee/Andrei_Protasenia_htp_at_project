package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.BookingSteps;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Booking2 {
    int daysFromNow = 10;
    int days = 5;
    static String BOOKING_URL = "https://www.booking.com/";
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
        BookingSteps.SetWindowMode(driver, BOOKING_URL, ScreenMode.MAXIMIZE);
    }

    @Test
    public void booking2() throws InterruptedException {
        BookingSteps.findElementKeys(driver, "//*[@id='ss']", "Moscow");
        BookingSteps.findElementEnter(driver, "//*[@data-mode='checkin']");
        BookingSteps.findElementEnter(driver, String.format("//*[@data-date='%s']", BookingSteps.setDays(daysFromNow)));
        BookingSteps.findElementEnter(driver, String.format("//*[@data-date='%s']", BookingSteps.setDays(daysFromNow + days)));
        BookingSteps.findElementEnter(driver, "(//*[contains(@type,'submit')])[1]");
        TimeUnit.SECONDS.sleep(3);
        Actions actions = new Actions(driver);
        WebElement chooseAdults = BookingSteps.findElementByCss(driver, "#group_adults");
        actions.click(chooseAdults).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        WebElement chooseRooms = BookingSteps.findElementByCss(driver, "#no_rooms");
        actions.click(chooseRooms).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        BookingSteps.findElementEnter(driver, "//*[contains(@type,'submit')]");
        TimeUnit.SECONDS.sleep(3);
        String budget = BookingSteps.findElementClickGetText(driver, "//*[@data-id='pri-1']");
        int pricePerNight = Integer.parseInt(budget.substring(budget.indexOf("-")).replaceAll("\\D+", ""));
        TimeUnit.SECONDS.sleep(3);
        String firstPrice = BookingSteps.findElementByText(driver, "(//*[contains(@class,'bui-price-display')]/div[2]/div)[1]").replaceAll("\\D+", "");
        int hotelPerNight = Integer.parseInt(firstPrice) / days;
        System.out.println("Price per night " + pricePerNight);
        System.out.println("Price per night of the first hotel" + hotelPerNight);
        assertTrue("Mistake", hotelPerNight <= pricePerNight);
    }
    @After
    public void postCondition() {
        BookingSteps.destroy(driver);
    }
}