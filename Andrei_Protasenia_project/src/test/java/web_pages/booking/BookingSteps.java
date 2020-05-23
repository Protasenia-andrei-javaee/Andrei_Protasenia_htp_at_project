package web_pages.booking;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.DriverSettings;
import settings.ScreenMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class BookingSteps {
    static WebElement element;
    static Actions actions;

    public static String setDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Properties getProperties(String path) throws IOException {
        Properties properties = new Properties();
        InputStream input = new FileInputStream(path);
        properties.load(input);
        return properties;
    }

    public static void SetWindowMode(WebDriver driver, String url, ScreenMode screenMode) {
        DriverSettings.setScreenMode(screenMode, driver);
        driver.get(url);
    }

    public static void findKeys(WebDriver driver, String xPath, String keys) {
        element = driver.findElement(By.xpath(xPath));
        element.sendKeys(keys);
    }

    public static void findElementEnter(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
    }

    public static WebElement findElementClickReturn(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
        return element;
    }

    public static String findElementGetAttribute(WebDriver driver, String xPath, String attribute) {
        return driver.findElement(By.xpath(xPath)).getAttribute(attribute);
    }

    public static void findElementClickRepeat(WebDriver driver, String xPath, int startAmount, int finishAmount) {
        element = driver.findElement(By.xpath(xPath));
        for (int i = 0; i < (finishAmount - startAmount); i++)
            element.click();
    }

    public static String findElementByText(WebDriver driver, String xPath) {
        return driver.findElement(By.xpath(xPath)).getText();
    }

    public static WebElement findElementByCss(WebDriver driver, String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public static String findElementClickGetText(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
        return element.getText();
    }

    public static void findElementHighlight(WebDriver driver, String xPath) {
        actions = new Actions(driver);
        element = driver.findElement(By.xpath(xPath));
        actions.moveToElement(element).perform();
    }

    public static void findElementCheckAttribute(WebDriver driver, String xPath, String attribute, String expected) {
        String getAttribute = driver.findElement(By.xpath(xPath)).getAttribute(attribute);
        assertEquals("Something wrong", expected, getAttribute);
    }

    public static void findElementScroll(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void findElementSetAttribute(WebDriver driver, String xPath, String attribute, String value) {
        element = driver.findElement(By.xpath(xPath));
        ((JavascriptExecutor) driver).executeScript(String.format("arguments[0].style.%s='%s'", attribute, value), element);
    }

    public static void destroy(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}

