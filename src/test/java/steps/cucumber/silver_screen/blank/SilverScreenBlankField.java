package steps.cucumber.silver_screen.blank;

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

import static org.junit.Assert.assertTrue;

public class SilverScreenBlankField {
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenBlankField.class);
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
        LOGGER.info("The app opened");
    }

    @When("^I left blank (.*) field$")
    public void leftBlankLoginField(String field) throws InterruptedException {
        switch (field) {
            case ("login"):
                silverScreenPage.SilverScreenEmail(properties.getProperty("FAKE_PASSWORD"));
                LOGGER.info("Left blank login field");
                break;
            case ("password"):
                silverScreenPage.SilverScreenPassword(properties.getProperty("FAKE_EMAIL"));
                LOGGER.info("Left blank login field");
                break;
        }
    }

    @Then("^I see (.*) message$")
    public void seeMessage(String message) {
        assertTrue("This message isn't expected", silverScreenPage.bannerAboutMistake(message));
        LOGGER.info("I made sure that a recommendation message was shown");
    }
}