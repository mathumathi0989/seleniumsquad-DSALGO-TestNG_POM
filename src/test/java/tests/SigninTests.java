package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dsalgoPOM.HomePage;
import dsalgoPOM.SigninPage;
import utilities.DriverManager;

public class SigninTests {

	 private WebDriver driver;
	    private WebDriverWait wait;
	    private SigninPage signInPage;
	    private HomePage homepage;

	    @BeforeClass
	    public void setup() {
	        driver = DriverManager.getDriver();
	        signInPage = new SigninPage(driver);
	        homepage = new HomePage(driver);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
    }

    @BeforeMethod
    public void navigateToSignInPage() {
        driver.get(configReader.getProperty("url") + "/login");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean isElementPresent = (Boolean) js.executeScript(
                "return document.evaluate(\"//a[normalize-space()='Sign in']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue != null;");
        if (isElementPresent) {
            WebElement signInButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Sign in']")));
            signInButton.click();
        } else {
            System.out.println("Element not found");
        }
    }

    @Test
    public void testSignInWithBlankUsernameAndPassword() {
        signInPage.clickLoginButton();
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='id_username']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", usernameField);
        System.out.println("Validation Error Message: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out this field.");
       
    }

    @Test
    public void testSignInWithBlankPassword() {
        signInPage.enterUsername(configReader.getProperty("username"));
        signInPage.clickLoginButton();
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='id_password']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", passwordField);
        System.out.println("Validation Error Message: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out this field.");
        
    }

    @Test
    public void testSignInWithBlankUsername() {
        signInPage.enterPassword(configReader.getProperty("password"));
        signInPage.clickLoginButton();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert message: " + alert.getText());
    }

    @Test
    public void testSignInWithInvalidUsernameAndPassword() {
        signInPage.enterUsername("invalidusername");
        signInPage.enterPassword("invalidpassword");
        signInPage.clickLoginButton();
        String invalidMessage = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
        System.out.println(invalidMessage);
        Assert.assertEquals(invalidMessage, "Invalid username or password.");
        
    }

    @Test
    public void testSignInWithValidUsernameAndInvalidPassword() {
        signInPage.enterUsername(configReader.getProperty("username"));
        signInPage.enterPassword("invalidpassword");
        signInPage.clickLoginButton();
        String invalidMessage = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
        System.out.println(invalidMessage);
        Assert.assertEquals(invalidMessage, "Invalid username or password.");
       
    }

    @Test
    public void testSignInWithInvalidUsernameAndValidPassword() {
        signInPage.enterUsername("invalidusername");
        signInPage.enterPassword(configReader.getProperty("password"));
        signInPage.clickLoginButton();
        String invalidMessage = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
        System.out.println(invalidMessage);
        Assert.assertEquals(invalidMessage, "Invalid username or password.");
        
    }

    @Test
    public void testSignInWithValidUsernameAndPassword() {
        signInPage.enterUsername(configReader.getProperty("username"));
        signInPage.enterPassword(configReader.getProperty("password"));
        signInPage.clickLoginButton();
        String actualMessage = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
        Assert.assertEquals(actualMessage, "You are logged in");
     
    }
    
}
