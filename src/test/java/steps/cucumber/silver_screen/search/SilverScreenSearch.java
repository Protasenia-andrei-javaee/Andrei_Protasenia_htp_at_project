package steps.cucumber.silver_screen.search;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
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

public class SilverScreenSearch {
    private static final Logger LOGGER = LogManager.getLogger(SilverScreenSearch.class);
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

    @When("I search for <search word> word")
    public void searchTheWord() {
        silverScreenPage.findMovie(properties.getProperty("SEARCH_WORD"));
        LOGGER.info("I searched the word");
    }

    @Then("I see the list of movie items")
    public void listOfMovies() throws InterruptedException {
        silverScreenPage.listMovies();
        LOGGER.info("List of movie items enable");
        TimeUnit.SECONDS.sleep(3);
    }

    @And("each item name or description contains <search word>")
    public void checkFilmName() {
        assertTrue(silverScreenPage.checkWordName(properties.getProperty("SEARCH_WORD")));
        LOGGER.info("Film name contains the word");
    }

    @After
    public static void postCondition() {
        Driver.testDestroy();
    }
}