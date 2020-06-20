package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webdriver.Driver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SilverScreenPage {

    @FindBy(xpath = "(//*[name()='svg' and @id='svg-icon-search'])[1]")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@id='root']//descendant::input[@placeholder='Поиск']")
    private WebElement searchField;
    @FindBy(xpath = "//*[contains(text(),'привилегии')]")
    private WebElement SilverScreenLogIn;
    @FindBy(xpath = "//*[@type='email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordEnterField;
    @FindBy(xpath = "//*[contains(text(),'Войти')]")
    private WebElement enterButton;
    @FindBy(xpath = "(//*[@id='root']//descendant::span[contains(.,'уровень: ')])[1]")
    private WebElement SilverScreenLevel;
    @FindBy(xpath = "//*[@id='root']//descendant::span[contains(.,'Выйти')]")
    private WebElement SilverScreenLogOut;
    @FindBy(xpath = "//*[@id='root']/div[2]/div/div[1]/div[1]/div[3]/span")
    private WebElement SilverScreenDescription;
    @FindBy(xpath = "//span[contains(text(),'Пользователь не найден')]")
    private WebElement alertForUnregistered;

    protected Actions action;
    private static final String SILVERSCREEN_POSTER_XPATH = "//*[@poster]/../div/a/span";
    private static final String POSTER_NAME_XPATH = "(//*[@poster]/../div/a/span)[%s]";
    private static final String POSTER_FOR_FIELD_XPATH = "//div[contains(text(),'%s')]";

    public void SilverScreenEmail(String password) throws InterruptedException {
        action.moveToElement(SilverScreenLogIn).perform();
        passwordEnterField.sendKeys(password);
        enterButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void SilverScreenPassword(String email) throws InterruptedException {
        action.moveToElement(SilverScreenLogIn).perform();
        emailField.sendKeys(email);
        enterButton.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public SilverScreenPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.action = new Actions(driver);
    }

    public void signInSilverScreen(String email, String password) throws InterruptedException {
        action.moveToElement(SilverScreenLogIn).perform();
        emailField.sendKeys(email);
        passwordEnterField.sendKeys(password);
        enterButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void SilverScreenSignOut() {
        SilverScreenLogOut.click();
    }

    public boolean levelShow() {
        return SilverScreenLevel.isDisplayed();
    }

    public boolean bannerForUnregistered() {
        return alertForUnregistered.isDisplayed();
    }

    public boolean bannerAboutMistake(String field) {
        return Driver.getWebDriver().findElement(By.xpath(String.format(POSTER_FOR_FIELD_XPATH, field))).isDisplayed();
    }

    public void findMovie(String searchWord) {
        action.moveToElement(searchButton).perform();
        action.click(searchField).sendKeys(searchWord).build().perform();
    }

    public void listMovies() {
        searchField.sendKeys(Keys.ENTER);
    }

    public boolean checkWordName(String word) {
        Matcher matcher;
        Pattern pattern = Pattern.compile(word.toLowerCase());
        List<WebElement> titles = Driver.getWebDriver().findElements(By.xpath(SILVERSCREEN_POSTER_XPATH));
        for (int i = 0; i < titles.size(); i++) {
            matcher = pattern.matcher(Driver.getText(String.format(POSTER_NAME_XPATH, (i + 1))).toLowerCase());
            if (!matcher.find()) {
                Driver.findElementClick(String.format(POSTER_NAME_XPATH, i + 1));
                matcher = pattern.matcher(SilverScreenDescription.getText().toLowerCase());
                if (!matcher.find()) {
                    return false;
                }
                Driver.getWebDriver().navigate().back();
            }
        }
        return true;
    }
}