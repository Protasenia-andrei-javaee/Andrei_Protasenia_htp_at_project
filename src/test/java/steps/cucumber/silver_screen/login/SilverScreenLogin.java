package steps.cucumber.silver_screen.login;

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

public class SilverScreenLogin {
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenLogin.class);
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
        LOGGER.info("I opened the app");
    }

    @When("I login with <login> and <password>")
    public void login() throws InterruptedException {
        silverScreenPage.signInSilverScreen(properties.getProperty("EMAIL"), properties.getProperty("PASSWORD"));
        LOGGER.info("I signed in");
    }

    @Then("I can see Red Carpet Club <level> in upper right corner")
    public void redFieldClubLevel() throws InterruptedException {
        assertTrue("Red field club level is not here", silverScreenPage.levelShow());
        LOGGER.info("Red field club level is here ");
        TimeUnit.SECONDS.sleep(2);
        silverScreenPage.SilverScreenSignOut();
    }

    @After
    public void postCondition() {
        Driver.testDestroy();
    }
}