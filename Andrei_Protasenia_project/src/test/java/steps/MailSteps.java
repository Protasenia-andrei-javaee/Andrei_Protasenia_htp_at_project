package steps;

import org.openqa.selenium.WebDriver;
import properties.PropertyPath;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailSteps {

    public static void YandexMail(String sender, WebDriver driver) throws InterruptedException, IOException {
        driver.get("https://mail.yandex.ru/");
        Properties prop = BaseSteps.getProperties(PropertyPath.YANDEX_PATH);
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementEnter(driver, "//*[contains(@class, \"HeadBanner-Button-Enter\")]");
        BaseSteps.findElementKeys(driver, "//*[@id= \"passp-field-login\"]", prop.getProperty("EMAIL"));
        BaseSteps.findElementEnter(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementKeys(driver, "//*[@id= \"passp-field-passwd\"]", prop.getProperty("PASSWORD"));
        BaseSteps.findElementEnter(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(4);
        BaseSteps.findElementEnter(driver, String.format("//*[contains(text(), \"%s\")]", sender));
        TimeUnit.SECONDS.sleep(3);
    }

    public static void emailProperty(String newMail, String propertyPath) throws IOException {
        Properties prop = BaseSteps.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}
