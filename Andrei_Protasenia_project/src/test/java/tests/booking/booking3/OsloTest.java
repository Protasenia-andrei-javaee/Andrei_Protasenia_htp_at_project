package tests.booking.booking3;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.booking.HotelsPage;
import pages.booking.MainPage;
import settings.Config;
import settings.ScreenMode;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class OsloTest {
    int daysAmount = 1;
    int daysShift = 1;
    int adultNeed = 2;
    int roomNeed = 1;
    int childNeed = 1;
    WebElement element;
    static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(OsloTest.class);

    @BeforeClass
    public static void preCondition() {
        MyDriver.initDriver(Config.CHROME);
    }

    @Before
    public static void pre_condition() {
        MyDriver.initDriver(Config.CHROME);
        LOGGER.info("Start test");
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        MyDriver.setWindowMode( "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates( "Oslo", daysAmount, daysShift, adultNeed, childNeed, roomNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find hotels with 3 and 4 stars")
    public void iFindHotelsWithStars() throws InterruptedException {
        MyDriver.ElementClick( "//*[@data-id=\"class-3\"]");
        MyDriver.ElementClick( "//*[@data-id=\"class-4\"]");
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find hotel №{int} in list")
    public void iFindHotelInList(Integer int1) throws InterruptedException {
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I'm changing background and text color")
    public void iMChangingBackgroundAndTextColor() {
        Actions actions = new Actions(driver);
        element = HotelsPage.executorSetBackgroundTitleColor(element, driver, actions);
    }

    @Then("I check that the text color is red")
    public void iCheckThatTheTextColorIsRed() {
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        Assert.assertEquals("color: red;", textColor);
    }


    @Test
    public void booking3Test() throws InterruptedException {
        MyDriver.setWindowMode( "https://www.booking.com/", ScreenMode.MAXIMIZE);
        MainPage.setCityPersonRoomDates( "Oslo", daysAmount, daysShift, adultNeed, childNeed, roomNeed);
        TimeUnit.SECONDS.sleep(4);

        MyDriver.ElementClick( "//*[@data-id=\"class-3\"]");
        MyDriver.ElementClick( "//*[@data-id=\"class-4\"]");
        TimeUnit.SECONDS.sleep(4);
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        TimeUnit.SECONDS.sleep(2);

        Actions actions = new Actions(driver);
        element = HotelsPage.executorSetBackgroundTitleColor(element, driver, actions);

        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        Assert.assertEquals("color: red;", textColor);
    }

    @After
    public static void post_condition() {
        MyDriver.destroyDriver();
        LOGGER.info("Finish test");
    }

    @AfterClass
    public static void postCondition() {
        MyDriver.destroyDriver();
    }
}
