package steps.cucumber.web.service;

import com.google.gson.Gson;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import steps.web.service.GetDataSteps;
import utilities.RequiredValues;
import utilities.Search;
import webdriver.Driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class WebService {
    private static final Logger LOGGER = LogManager.getLogger(WebService.class);
    static String WEB_SERVICE_CONDITIONS = "src/test/resources/properties/webPaths.properties";
    static Search search;
    static RequiredValues response;
    static RequiredValues preliminary;
    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;



    @Before
    public void before() throws IOException {
        LOGGER.info("Start");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = Driver.getProperties(WEB_SERVICE_CONDITIONS);
    }

    @Given("I search by {int} condition")
    public void searchByCondition(Integer condition) throws IOException {
        search = GetDataSteps.getDataForRequest(gson, condition, paths);
    }

    @When("I get response")
    public void getResponse() throws IOException, URISyntaxException {
        response = getDataSteps.getResponse(gson, search);
    }

    @And("I get {string} names for comparison")
    public void getNames(String data) throws FileNotFoundException {
        preliminary = getDataSteps.getDataForWithResponse(gson, paths, data);
    }

    @Then("I compare response and preliminary data")
    public void checkResponseAndPreliminaryData() {
        LOGGER.debug("I compare response and preliminary data");
        assertEquals(response.hashCode(), preliminary.hashCode());
    }

    @After
    public void after() {
        LOGGER.info("Finish");
    }
}