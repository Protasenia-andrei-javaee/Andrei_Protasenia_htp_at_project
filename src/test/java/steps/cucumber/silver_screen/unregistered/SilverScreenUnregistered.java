package steps.cucumber.silver_screen.unregistered;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.Config;
import settings.ScreenMode;
import webdriver.Driver;
import pages.SilverScreenPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SilverScreenUnregistered {
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenUnregistered.class);
    Properties properties;
    SilverScreenPage silverScreenPage;
    static String SILVER_SCREEN_PATH = "src/test/resources/properties/silverScreen.properties";
    static String SILVER_SCREEN_URL = "https://silverscreen.by";


    @Before
    public void preCondition() throws IOException {
        properties = Driver.getProperties(SILVER_SCREEN_PATH);
        Driver.initDriver(Config.CHROME);
        silverScreenPage = new SilverScreenPage(Driver.getWebDriver());
    }

    @Given("I open an app")
    public void openApp() {
        Driver.setWindowMode(SILVER_SCREEN_URL, ScreenMode.MAXIMIZE);
        LOGGER.info("Open the app");
    }

    @When("I login as unregistered user")
    public void login() throws InterruptedException {
        silverScreenPage.signInSilverScreen(properties.getProperty("FAKE_EMAIL"), properties.getProperty("FAKE_PASSWORD"));
        LOGGER.info("Sign unregistered user");
    }

    @Then("I see validation message")
    public void alertMessage() throws InterruptedException {
        assertTrue("No warning message", silverScreenPage.bannerForUnregistered());
        LOGGER.info("User not found");
        TimeUnit.SECONDS.sleep(2);
    }

    @After
    public void postCondition() {
        Driver.testDestroy();
    }
}