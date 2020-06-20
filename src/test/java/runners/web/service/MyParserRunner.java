package runners.web.service;

import utilities.Search;
import utilities.MyHttpClient;
import utilities.Parser;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyParserRunner {
    static Parser parser = new Parser();

    public static void main(String[] args)
            throws IOException, URISyntaxException {
        parser.parseJSON();
        parser.parseGSON();
        parser.parseJackson();
        parser.fromGSON();
        MyHttpClient httpClient = new MyHttpClient();
        Search searchData = new Search("berta", true);
        httpClient.search(searchData);
    }
}