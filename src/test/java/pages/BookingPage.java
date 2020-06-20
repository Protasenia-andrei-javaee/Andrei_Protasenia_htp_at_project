package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webdriver.Driver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookingPage {

    @FindBy(xpath = "//*[@name='ss']")
    private static WebElement citySelection;
    @FindBy(xpath = "//div[@class='xp__dates-inner']")
    private static WebElement dropDawnCalendar;
    @FindBy(xpath = "//*[@class='xp__input']")
    private static WebElement dropDawnAdultsChildrenRooms;
    @FindBy(xpath = "//*[@id='group_adults']")
    private static WebElement numberOfAdults;
    @FindBy(xpath = "//*[@id='no_rooms']")
    private static WebElement numberOfRooms;
    @FindBy(xpath = "//*[@id='group_children']")
    private static WebElement numberOfChildren;
    @FindBy(xpath = "//*[contains(@type,'submit')]")
    private static WebElement searchBoxButton;
    @FindBy(xpath = "//*[@id='sort_by']/ul/li[3]/a")
    private static WebElement lowestPriceFirstOption;
    @FindBy(xpath = "//a[@data-id='pri-5']")
    private static WebElement maxBudgetCheckBox;
    @FindBy(xpath = "//*[contains(@class,'prco-wrapper')]/div[2]/div")
    private static WebElement firstHotelInTable;
    @FindBy(xpath = "//a[@data-id='pri-1']")
    private static WebElement minBudgetCheckBox;
    @FindBy(xpath = "//*[@id='filter_class']/div[2]/a[2]")
    private static WebElement threeStarRatingCheckBox;
    @FindBy(xpath = "//*[@id='filter_class']/div[2]/a[3]")
    private static WebElement fourStarRatingCheckBox;
    @FindBy(xpath = "//*[@id='hotellist_inner']/div[1]/div[1]/div/button")
    private static WebElement heartRadioButton;
    @FindBy(xpath = "//*[contains(@class,'mydashboard')]")
    private static WebElement accountDashBoard;
    @FindBy(xpath = "//*[contains(@class,'list_item_desc')]")
    private static WebElement wishList;
    @FindBy(xpath = "//*[contains(@data-index,'0')]/div/a")
    private static WebElement deleteFirstSavedHotel;
    @FindBy(xpath = "//*[contains(@data-index,'1')]/div/a")
    private static WebElement deleteSecondSavedHotel;
    @FindBy(xpath = "//*[@id='username']")
    private static WebElement login;
    @FindBy(xpath = "//*[@type='submit']")
    private static WebElement createAccountButton;
    @FindBy(xpath = "//*[@id='password']")
    private static WebElement password;
    @FindBy(xpath = "//*[@id='password']")
    private static WebElement enterPassword;
    @FindBy(xpath = "//input[@id='confirmed_password']")
    private static WebElement confirmPassword;
    @FindBy(xpath = "//*[@id='login_name_register']")
    private static WebElement enterLogin;
    @FindBy(xpath = "//*[contains(@class,'nw-register')]/button")
    private static WebElement start;
    @FindBy(xpath = "//*[@id='current_account_create']")
    private static WebElement createAccount;
    @FindBy(xpath = "//*[@id='current_account']")
    private static WebElement signIn;
    @FindBy(xpath = "//*[contains(@id,'current_account')]")
    private static WebElement currentAccount;

    private static final String CHOOSE_DATE_XPATH = "//*[contains(@data-date,'%s')]";
    private static final String ADULTS_AMOUNT_XPATH = "//*[contains(@aria-describedby,'adult')][contains(@class,'add')]";
    private static final String ROOMS_AMOUNT_XPATH = "//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]";
    private static final String CHILDREN_AMOUNT_XPATH = "//*[contains(@aria-describedby,'group_children_desc')][contains(@class,'add')]";
    private static final String ACTIONS_ADULTS_XPATH = "//*[@id='group_adults']";
    private static final String ACTIONS_ROOMS_XPATH = "//*[@id='no_rooms']";
    private static final String SUBMIT_ON_SECOND_PAGE_XPATH = "//*[@data-sb-id='main'][contains(@type,'submit')]";
    private static final String MIN_INTERVAL_OF_BUDGET_XPATH = "//*[@id='filter_price']//a[1]";
    private static final String TENTH_HOTEL_XPATH = "//*[@data-hotelid][10]";
    private static final String TENTH_HOTEL_TITLE_XPATH = "%s//span[contains(@class,'sr-hotel__name')]";
    private static final String FIRST_ID_XPATH = "//*[@id='hotellist_inner']/div[1]/div[1]/div/button";
    private static final String FIRST_FILL_XPATH = "//*[@id='hotellist_inner']/div[1]/div[1]/div/button/*[1]";
    private static final String LAST_HEART_XPATH = "//*[@id='hotellist_inner']/div[%s]/div[1]/div/button";
    private static final String LAST_FILL_XPATH = "//*[@id='hotellist_inner']/div[%s]/div[1]/div/button/*[1]";
    private static final String AMOUNT_FOR_LIST_XPATH = "//*[@id='hotellist_inner']/div";
    private static final String FIRST_FAVOURITE_ID_XPATH = "//*[contains(@data-index,'0')]/div";
    private static final String SECOND_FAVOURITE_ID_XPATH = "//*[contains(@data-index,'1')]/div";

    private static final String HOTEL_ID_ATTRIBUTE = "data-hotel-id";
    private static final String HOTEL_FILL_ATTRIBUTE = "fill";
    private static final String WISH_LIST_ID_ATTRIBUTE = "data-id";
    private static final String RED = "rgb(204, 0, 0)";

    private static final String LOGO_XPATH = "//*[@class='header-wrapper']/img";
    private static final String CURRENCY_XPATH = "//*[@data-id='currency_selector']";
    private static final String LANGUAGE_XPATH = "//*[@data-id='language_selector']";
    private static final String NOTIFICATIONS_XPATH = "//*[@data-id='notifications']";
    private static final String HELP_CENTER_XPATH = "//*[contains(@class,'uc_helpcenter')]";
    private static final String LIST_PROPERTY_XPATH = "//*[contains(@class,'uc_account logged')]";
    private static final String CURRENT_ACCOUNT_XPATH = "//*[contains(@id,'current_account')]";
    private static final String ACCOMMODATION_XPATH = "//*[contains(@data-ga-track,'accommodation')]";
    private static final String FLIGHTS_XPATH = "//*[contains(@data-ga-track,'flights')]";
    private static final String CARS_XPATH = "//*[contains(@data-ga-track,'cars')]";
    private static final String ATTRACTIONS_XPATH = "//*[contains(@data-ga-track,'attractions')]";
    private static final String AIRPORT_TAXIS_XPATH = "//*[contains(@data-ga-track,'airport_taxis')]";
    private static final Logger LOGGER = LogManager.getLogger(BookingPage.class);
    protected Actions actions;
    public WebElement element;
    private String firstFavoriteHotel;
    private String lastFavoriteHotel;
    public String getFirstHotel() {
        return firstFavoriteHotel;
    }
    public String getSecondHotel() {
        return lastFavoriteHotel;
    }

    public BookingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.actions = new Actions(driver);
    }

    public void setData(String city, int daysAmount, int daysShift, int adultsNeed, int childrenNeed, int roomsNeed) throws InterruptedException {
        LOGGER.debug("Set city");
        citySelection.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
        LOGGER.debug("Set period");
        dropDawnCalendar.click();
        Driver.findElementClick(String.format(CHOOSE_DATE_XPATH, setDays(daysShift)));
        Driver.findElementClick(String.format(CHOOSE_DATE_XPATH, setDays(daysAmount + daysShift)));
        dropDawnAdultsChildrenRooms.click();
        LOGGER.debug("Set adults value");
        int adultsAmount = Integer.parseInt(numberOfAdults.getAttribute("value"));
        Driver.multiplyClick(ADULTS_AMOUNT_XPATH, adultsAmount, adultsNeed);
        LOGGER.debug("Set rooms value");
        int roomsAmount = Integer.parseInt(numberOfRooms.getAttribute("value"));
        Driver.multiplyClick(ROOMS_AMOUNT_XPATH, roomsAmount, roomsNeed);
        LOGGER.debug("Set children value");
        int childrenAmount = Integer.parseInt(numberOfChildren.getAttribute("value"));
        Driver.multiplyClick(CHILDREN_AMOUNT_XPATH, childrenAmount, childrenNeed);
        LOGGER.debug("Search");
        searchBoxButton.click();
        TimeUnit.SECONDS.sleep(5);
    }

    public static String setDays(int daysAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }

    public void sortForParis() throws InterruptedException {
        LOGGER.debug("Sort for Paris lowest price");
        lowestPriceFirstOption.click();
        LOGGER.debug("Sort for Paris max budget");
        maxBudgetCheckBox.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void checkParisHotels(int daysAmount) {
        LOGGER.debug("Check hotels");
        String maxBudget = maxBudgetCheckBox.getText().replaceAll("\\D+", "");
        String firstPrice = firstHotelInTable.getText().replaceAll("\\D+", "");
        int firstOneNightPrice = Integer.parseInt(firstPrice) / daysAmount;
        System.out.println("Budget: " + maxBudget + "+");
        System.out.println("One night price: " + firstOneNightPrice);
        assertTrue(firstOneNightPrice >= Integer.parseInt(maxBudget));
    }

    public void actionsForMoscow() throws InterruptedException {
        LOGGER.debug("Properties in Moscow trip");
        element = Driver.elementReturn(ACTIONS_ADULTS_XPATH);
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = Driver.elementReturn(ACTIONS_ROOMS_XPATH);
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(Driver.elementReturn(SUBMIT_ON_SECOND_PAGE_XPATH)).click().perform();
        TimeUnit.SECONDS.sleep(3);
    }

    public void sortMoscow() throws InterruptedException {
        LOGGER.debug("Sort for minimum budget");
        minBudgetCheckBox.click();
        TimeUnit.SECONDS.sleep(3);
    }

    public void checkMoscowHotels(int daysAmount) throws InterruptedException {
        LOGGER.debug("Check hotels");
        element = Driver.elementReturn(MIN_INTERVAL_OF_BUDGET_XPATH);
        String minBudget = element.getText();
        minBudget = minBudget.substring(minBudget.indexOf("-")).replaceAll("\\D+", "");
        TimeUnit.SECONDS.sleep(3);
        String firstPrice = firstHotelInTable.getText().replaceAll("\\D+", "");
        int firstOneNightPrice = Integer.parseInt(firstPrice) / (daysAmount);
        System.out.println("Budget: " + minBudget);
        System.out.println("One night price: " + firstOneNightPrice);
        assertTrue(firstOneNightPrice <= Integer.parseInt(minBudget));
    }

    public void sortOslo() throws InterruptedException {
        LOGGER.debug("Sort for Oslo three-four star hotels");
        threeStarRatingCheckBox.click();
        fourStarRatingCheckBox.click();
        TimeUnit.SECONDS.sleep(4);
    }

    public void actionsOslo() throws InterruptedException {
        LOGGER.debug("Find hotel and check color for Oslo");
        element = Driver.elementReturn(TENTH_HOTEL_XPATH);
        element = setColorAndCheckTitle(Driver.getWebDriver(), element);
        String titleColor = element.getAttribute("style");
        if (titleColor.equals("color: red;"))
            System.out.println("Red color confirmed");
        assertEquals("color: red;", titleColor);
    }

    public WebElement setColorAndCheckTitle(WebDriver driver, WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        LOGGER.debug("Finding 10th hotel on page");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        actions.moveToElement(driver.findElement(By.xpath(String.format(TENTH_HOTEL_TITLE_XPATH, TENTH_HOTEL_XPATH)))).perform();
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='green'", element);
        LOGGER.debug("Changing color to green");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        element = driver.findElement(By.xpath(String.format(TENTH_HOTEL_TITLE_XPATH, TENTH_HOTEL_XPATH)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color='red'", element);
        LOGGER.debug("Changing title's color to red");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        return element;
    }

    public void checkHeader() {
        LOGGER.debug("Check headers");
        assertTrue(Driver.itemDisplayed(LOGO_XPATH));
        assertTrue(Driver.itemDisplayed(CURRENCY_XPATH));
        assertTrue(Driver.itemDisplayed(LANGUAGE_XPATH));
        assertTrue(Driver.itemDisplayed(NOTIFICATIONS_XPATH));
        assertTrue(Driver.itemDisplayed(HELP_CENTER_XPATH));
        assertTrue(Driver.itemDisplayed(LIST_PROPERTY_XPATH));
        assertTrue(Driver.itemDisplayed(CURRENT_ACCOUNT_XPATH));
        assertTrue(Driver.itemDisplayed(ACCOMMODATION_XPATH));
        assertTrue(Driver.itemDisplayed(FLIGHTS_XPATH));
        assertTrue(Driver.itemDisplayed(CARS_XPATH));
        assertTrue(Driver.itemDisplayed(ATTRACTIONS_XPATH));
        assertTrue(Driver.itemDisplayed(AIRPORT_TAXIS_XPATH));
    }

    public void signIn(Properties properties) throws InterruptedException {
        LOGGER.debug("Sign in to booking.com account");
        Driver.getWebDriver().get("https://www.booking.com/");
        signIn.click();
        TimeUnit.SECONDS.sleep(3);
        login.sendKeys(properties.getProperty("NEW_MAIL"));
        createAccountButton.click();
        TimeUnit.SECONDS.sleep(1);
        password.sendKeys(properties.getProperty("PASSWORD"));
        createAccountButton.click();
    }

    public void setFavoriteHotel() throws InterruptedException {
        LOGGER.debug("Set first hotel");
        heartRadioButton.click();
        element = Driver.elementReturn(FIRST_ID_XPATH);
        firstFavoriteHotel = element.getAttribute(HOTEL_ID_ATTRIBUTE);
        element = Driver.elementReturn(FIRST_FILL_XPATH);
        TimeUnit.SECONDS.sleep(3);
        assertEquals(RED, element.getCssValue(HOTEL_FILL_ATTRIBUTE));
        LOGGER.debug("Set second hotel");
        List<WebElement> list = Driver.getWebDriver().findElements(By.xpath(AMOUNT_FOR_LIST_XPATH));
        Driver.findElementClick(String.format(LAST_HEART_XPATH, (list.size() - 1)));
        TimeUnit.SECONDS.sleep(3);
        element = Driver.elementReturn(String.format(LAST_HEART_XPATH, (list.size() - 1)));
        lastFavoriteHotel = element.getAttribute(HOTEL_ID_ATTRIBUTE);
        element = Driver.elementReturn(String.format(LAST_FILL_XPATH, (list.size() - 1)));
        assertEquals(RED, element.getCssValue(HOTEL_FILL_ATTRIBUTE));
        System.out.println(firstFavoriteHotel + " " + lastFavoriteHotel);
    }

    public void checkHotelID(String firstFavoriteHotel, String lastFavoriteHotel) throws InterruptedException {
        LOGGER.debug("Check hotel ID list");
        currentAccount.click();
        accountDashBoard.click();
        TimeUnit.SECONDS.sleep(3);
        wishList.click();
        TimeUnit.SECONDS.sleep(5);
        element = Driver.elementReturn(FIRST_FAVOURITE_ID_XPATH);
        assertEquals(firstFavoriteHotel, element.getAttribute(WISH_LIST_ID_ATTRIBUTE));
        element = Driver.elementReturn(SECOND_FAVOURITE_ID_XPATH);
        assertEquals(lastFavoriteHotel, element.getAttribute(WISH_LIST_ID_ATTRIBUTE));
        deleteFirstSavedHotel.click();
        deleteSecondSavedHotel.click();
    }

    public void registration(Properties properties, String BOOKING_PATH) throws InterruptedException, IOException {
        LOGGER.debug("Booking.com registration");
        properties = Driver.getProperties(BOOKING_PATH);
        createAccount.click();
        TimeUnit.SECONDS.sleep(1);
        LOGGER.debug("Printing email");
        enterLogin.sendKeys(properties.getProperty("NEW_MAIL"));
        start.click();
        TimeUnit.SECONDS.sleep(1);
        LOGGER.debug("Printing password twice");
        password.sendKeys(properties.getProperty("PASSWORD"));
        confirmPassword.sendKeys(properties.getProperty("PASSWORD"));
        searchBoxButton.click();
    }
}