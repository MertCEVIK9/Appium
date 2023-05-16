package LifeboxTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static LifeboxTest.TakeData.getDesiredCapabilities;


@RunWith(Cucumber.class)
public class Login {


public IOSDriver<MobileElement> driver;
    WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException {
        URL remoteUrl = new URL("http://127.0.0.1:4723/");
        driver = new IOSDriver(remoteUrl, getDesiredCapabilities("iPhone6sPlus"));
        wait = new WebDriverWait(driver, 10);
    }




    @Given("click {string} button")
    public void click(String buttonName) {

        String buttonXpath = TakeData.getXPath(buttonName);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
        MobileElement el1 = driver.findElementByXPath(buttonXpath);
        el1.click();

    }

    @And("scroll down")
    public void scrollDown() {
        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
    }


    @Given("enter {string} credential")
    public void enterCredential(String Credentials) {

        String userName = TakeData.getUser(Credentials,"UserName");
        String password = TakeData.getUser(Credentials,"PassWord");

        String userNameXPath = TakeData.getXPath("UserNameXPath");
        String passwordXPath = TakeData.getXPath("PassWordXPath");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(userNameXPath)));

        MobileElement el2 = driver.findElementByXPath(userNameXPath);
        el2.sendKeys(userName);

        MobileElement el4 = driver.findElementByXPath(passwordXPath);
        el4.sendKeys(password);


    }

    @And("close {string}")
    public void AutoSync(String action){

        String autoSync = TakeData.getXPath(action);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(autoSync)));

        MobileElement autoSyncSwitch = driver.findElementByXPath(autoSync);

        if (autoSyncSwitch.getAttribute("value").equals("1")) {
            autoSyncSwitch.click();
        }


    }

    @Then("see {string} tab")
    public void seeTab(String arg0) {

        wait.until(ExpectedConditions.elementToBeClickable(By.id(arg0)));


        MobileElement el6 = driver.findElementByAccessibilityId(arg0);
        Assert.assertTrue(el6.getAttribute("value").matches("1"));


    }

    @And("click first image")
    public void clickImage() {
        MobileElement el3 = driver.findElementByXPath("(//XCUIElementTypeImage[@name=\"senna.jpg\"])[1]/XCUIElementTypeOther/XCUIElementTypeOther");
        el3.click();
    }



}
