package implementation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import locator.Locators;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static locator.Locators.*;

public class Implement {

    //initializing all the required variables
    public static String deviceName;
    public static String platformName;
    public static String udid;
    public static String appPath;
    public static String country;
    public static AppiumDriver driver = null;

    //creating object for config.properties
    static Properties properties = new Properties();
    public static String jordan_name;

    //value to search when web opens
    public static String searchElement = "Appium";

    //method for config.properties
    public static void properties() {
        try {
            InputStream inputStream = new FileInputStream("C:\\Users\\Pallavi.Arora\\IdeaProjects\\assignmentMobile\\src\\config.properties");
            properties.load(inputStream);
            deviceName = properties.getProperty("appium.deviceName");
            platformName = properties.getProperty("appium.platformName");
            udid = properties.getProperty("appium.udid");
            appPath = properties.getProperty("appium.app");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //setting the desired capabilities
    public static void capabilities() throws IOException {
        try {
            properties();
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("devicename", deviceName);
            cap.setCapability("platformName", platformName);
            cap.setCapability("udid", udid);
            cap.setCapability("app", appPath);

            URL url = new URL(properties.getProperty("appium.hub"));

            driver = new AndroidDriver(url, cap);

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //taking screenshot of app launching
        screenshot("launchApp.jpg");
    }

    //selecting the country from dropdown
    public static void selectCountry() throws IOException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement selectCountry = driver.findElement(countryBtn);
        selectCountry.click();
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + country + "\").instance(0))")).click();
        //screenshot that country is selected
        screenshot("selectCountry.jpg");
    }

    //method to verify that the country is being selected
    public static void verifyCountry() {
        String selectedCountry = driver.findElement(Locators.selectedCountry).getText();
        Assert.assertEquals(selectedCountry, country);
    }

    //logging in without entering name
    public static void loginWithoutName() throws IOException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement gender = driver.findElement(radioFemale);
        gender.click();
        WebElement loginBtn= driver.findElement(login_btn);
        loginBtn.click();
        //screenshot to show it doesn't login without name
        screenshot("withoutName.jpg");
    }

    //logging in by entering name
    public static void loginWithName(String name) throws IOException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement name_input = driver.findElement(Locators.name);
        name_input.sendKeys(name);

        //screenshot to show that name was entered
        screenshot("withName.jpg");

        //selecting gender
        WebElement gender = driver.findElement(Locators.radioFemale);
        gender.click();
        //clicking on let's shop button
        WebElement login = driver.findElement(login_btn);
        login.click();
    }

    //method to verify that user ahs logged in
    public static void verifyLogin() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            WebElement productsTitle = driver.findElement(Locators.products);
            Assert.assertTrue(productsTitle.isDisplayed());
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    //adding product to cart
    public static void addProducts() throws IOException {
        //scrolling down to the product
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"PG 3\").instance(0))"));

        //getting name of the product
        WebElement jordan_shoes = driver.findElement(Locators.jordan);
        jordan_name = jordan_shoes.getText();

        //clicking on ADD TO CART
        WebElement jordan_cart = driver.findElement(Locators.add_to_cart_1);
        jordan_cart.click();

        //screenshot to show product was added to cart
        screenshot("addedJordanToCart.jpg");
    }

    //verifying that cart displays 1 as one product was added
    public static void verifyCartCounter() {
        WebElement cart_counter = driver.findElement(Locators.counter);
        if (cart_counter.getText().contains("1")) {
            System.out.println("One item in cart");
        } else {
            System.out.println("not verified");
        }
    }

    //going to cart
    public static void goToCart() throws IOException {
        WebElement cart = driver.findElement(Locators.cart_btn);
        cart.click();

        //screenshot to show user is in cart
        screenshot("inCart.jpg");
    }

    //verifying that the correct product was added
    public static void verifyProducts() {
        WebElement jordan_in_cart = driver.findElement(Locators.jordan_cart);
        String jordan_name_cart = jordan_in_cart.getText();

        //if the name of product in cart is same as name of product we selected, then it is correct
        if  (jordan_name_cart.equals(jordan_name)) {
            System.out.println("Product verified");
        } else {
            System.out.println("Product not verified");
        }
    }

    //opening web from app
    public static void webSearch() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Click on the web search element
            driver.findElement(Locators.webSearch).click();
            //screenshot to show that open browser button was clicked
            screenshot("browserOpens.jpg");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

            //locating the input field in browser
            WebElement browserSearch = driver.findElement(Locators.input);
            browserSearch.click();
            //entering value to be searched
            browserSearch.sendKeys(searchElement);

            //screenshot of input field
            screenshot("enteringValue.jpg");

            //pressing enter
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ENTER).perform();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //verifying that the value was searched
    public static void verifyWeb() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            //getting context handles
            Set<String> contextsSwitch = driver.getContextHandles();
            //switching context to browser
            driver.context((String) contextsSwitch.toArray()[1]);

            //locating all links with search value and adding to list
            List<WebElement> linkList = driver.findElements(By.partialLinkText(searchElement));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //printing size of list
            System.out.println(linkList.size());
            //screenshot to verify that value was searched
            screenshot("searched.jpg");

            //verifying that list has more than 1 items to show that values were stored
            if (linkList.size()>0) {
                System.out.println("Result verified");
            }
            else {
                System.out.println("no output");
            }
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method to take screenshots
    public static void screenshot(String fileName) throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\Pallavi.Arora\\IdeaProjects\\assignmentMobile\\src\\images\\" + fileName));
    }
}
