package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import settings.Config;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    public static WebDriver getDriver(Config config) throws MalformedURLException {
        switch (config) {
            case CHROME:
                return getChromeDriver();
            default:
                throw null;
        }
    }
    private static WebDriver getRemoteDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }
    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
            return new ChromeDriver();
    }


}