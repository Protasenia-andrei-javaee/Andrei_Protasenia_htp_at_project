package steps.web.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utilities.RequiredValues;
import utilities.Search;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class GetDataSteps {
    private static final Logger LOGGER = LogManager.getLogger(GetDataSteps.class);

    public static Search getDataForRequest(Gson gson, int condition, Properties paths) throws FileNotFoundException {
        LOGGER.debug("I get data for request");
        Search[] searchData = gson.fromJson(new JsonReader(new FileReader(paths.getProperty("JSON"))), Search[].class);
        return searchData[condition];
    }

    public RequiredValues getResponse(Gson gson, Search search) throws IOException, URISyntaxException {
        return gson.fromJson(HttpRequestSteps.getHttpResponse(gson, search), RequiredValues.class);
    }

    public RequiredValues getDataForWithResponse(Gson gson, Properties paths, String condition) throws FileNotFoundException {
        LOGGER.debug("I get data with response");
        return gson.fromJson(new JsonReader(new FileReader(paths.getProperty(condition))), RequiredValues.class);
    }
}