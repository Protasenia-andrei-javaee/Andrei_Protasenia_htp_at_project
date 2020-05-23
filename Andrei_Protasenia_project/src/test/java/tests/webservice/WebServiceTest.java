package tests.webservice;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.WebService.WebServiceSteps;
import steps.WebService.HttpSteps;
import utills.Search;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceTest {

    static Gson gson;

    @BeforeClass
    public static void preCondition() {
        gson = new Gson();
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        Search search = WebServiceSteps.searchDataFromFile(gson, 0);
        String response = HttpSteps.setHttp(gson, search);
        List<String> list = WebServiceSteps.usernames(response);
        list.forEach(System.out::println);
        Assert.assertEquals(6, list.size());
    }
    @Test
    public void shortTest() throws IOException, URISyntaxException {
        Search search = WebServiceSteps.searchDataFromFile(gson, 1);
        String response = HttpSteps.setHttp(gson, search);
        List<String> list = WebServiceSteps.usernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebServiceSteps.partialCheck(list,"a"));
    }
    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        Search search = WebServiceSteps.searchDataFromFile(gson, 2);
        String response = HttpSteps.setHttp(gson, search);
        List<String> list = WebServiceSteps.usernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebServiceSteps.checker(list,"a"));
    }
    @Test
    public void longTest() throws IOException, URISyntaxException {
        Search search = WebServiceSteps.searchDataFromFile(gson, 3);
        String response = HttpSteps.setHttp(gson, search);
        List<String> list = WebServiceSteps.usernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebServiceSteps.partialCheck(list,"al"));
    }
    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        Search search = WebServiceSteps.searchDataFromFile(gson, 4);
        String response = HttpSteps.setHttp(gson, search);
        List<String> list = WebServiceSteps.usernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebServiceSteps.checker(list,"saldo"));
    }

}
