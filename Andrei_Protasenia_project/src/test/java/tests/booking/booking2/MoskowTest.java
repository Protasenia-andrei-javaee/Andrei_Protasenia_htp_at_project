package tests.booking.booking2;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.booking.MainPage;
import settings.Config;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class MoskowTest {
    int daysAmount = 5;
    int daysShift = 10;
    int firstOneDayPrice;
    WebElement element;
    static WebDriver driver;
    String maxPrice;

    private static final Logger LOGGER = LogManager.getLogger(MoskowTest.class);
    @Before
    public void pre_condition() {
        MyDriver.initDriver(Config.CHROME);
        LOGGER.info("Start test");
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        driver.get("https://www.booking.com/");
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates( "Moscow", daysAmount, daysShift, 2, 0, 1);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I enter alders amount by actions")
    public void iEnterAldersAmountByActions() throws InterruptedException {
        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id=\"group_adults\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = driver.findElement(By.xpath("//*[@id=\"no_rooms\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-sb-id=\"main\"][contains(@type, \"submit\")]"))).click().perform();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I filter hotels at the minimum price")
    public void iFilterHotelsAtTheMinimumPrice() throws InterruptedException {
        MyDriver.ElementClick( "//*[contains(@class, \"sort_price\")]/a");
        element = MyDriver.findElementClickReturn( "//*[@id=\"filter_price\"]//a[1]");
        maxPrice = element.getText();
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
        String firstPrice = MyDriver.findElementGetText( "//*[contains(@class, \"bui-price-display\")]/div[2]/div");
        firstPrice = firstPrice.replaceAll("\\D+", "");
        firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
    }

    @Then("I compare hotel's price and price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
    }


    @Test
    public void booking2Test() throws InterruptedException {
        driver.get("https://www.booking.com/");
        MainPage.setCityPersonRoomDates("Moscow", daysAmount, daysShift, 2, 0, 1);
        TimeUnit.SECONDS.sleep(3);

        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id=\"group_adults\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = driver.findElement(By.xpath("//*[@id=\"no_rooms\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-sb-id=\"main\"][contains(@type, \"submit\")]"))).click().perform();
        TimeUnit.SECONDS.sleep(2);
        MyDriver.ElementClick( "//*[contains(@class, \"sort_price\")]/a");
        element = MyDriver.findElementClickReturn( "//*[@id=\"filter_price\"]//a[1]");
        String maxPrice = element.getText();
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);
        String firstPrice = MyDriver.findElementGetText( "//*[contains(@class, \"bui-price-display\")]/div[2]/div");
        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));

    }

    @After
    public static void post_condition() {
        MyDriver.destroyDriver();
        LOGGER.info("Finish test");
    }

}
