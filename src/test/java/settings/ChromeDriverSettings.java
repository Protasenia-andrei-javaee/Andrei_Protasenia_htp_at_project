package settings;

import org.openqa.selenium.WebDriver;

public class ChromeDriverSettings {
    public static void setScreenMode(ScreenMode mode, WebDriver driver) {
        switch (mode) {
            case FULL_SCREEN:
                setWindowMode(driver);
            case MAXIMIZE:
                setMaximizeMode(driver);
        }
    }
    private static void setWindowMode(WebDriver driver) {
        driver.manage().window().fullscreen();
    }
    private static void setMaximizeMode(WebDriver driver) {
        driver.manage().window().maximize();
    }


}