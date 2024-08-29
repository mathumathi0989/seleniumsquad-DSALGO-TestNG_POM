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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dsalgoPOM.BaseTest;
import dsalgoPOM.HomePage;
import dsalgoPOM.SigninPage;
import utilities.ConfigReader;
import utilities.DriverManager;

public class SigninTests extends BaseTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private SigninPage signInPage;
	private HomePage homepage;

	@BeforeMethod
	public void setUp() {
		// Initialize driver from BaseTest or DriverManager
		this.driver = DriverManager.getDriver(); // or driver = super.driver;

		// Initialize WebDriverWait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Initialize Page Objects
		homepage = new HomePage(driver);
		signInPage = new SigninPage(driver);

		// Navigate to the sign-in page
		driver.get(ConfigReader.getProperty("url"));
		homepage.clickGetStarted();

		// Check if Sign In button is present and click it
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
		signInPage.enterUsername(ConfigReader.getProperty("username"));
		signInPage.clickLoginButton();
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='id_password']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", passwordField);
		System.out.println("Validation Error Message: " + validationMessage);
		Assert.assertEquals(validationMessage, "Please fill out this field.");
	}

	@Test
	public void testSignInWithBlankUsername() {
		signInPage.enterPassword(ConfigReader.getProperty("password"));
		signInPage.clickLoginButton();
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='id_username']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", passwordField);
		System.out.println("Validation Error Message: " + validationMessage);
		Assert.assertEquals(validationMessage, "Please fill out this field.");

	}

	@Test
	public void testSignInWithInvalidUsernameAndPassword() {
		signInPage.enterUsername("invalidusername");
		signInPage.enterPassword("invalidpassword");
		signInPage.clickLoginButton();
		String invalidMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
		System.out.println(invalidMessage);
		Assert.assertEquals(invalidMessage, "Invalid Username and Password");
	}

	@Test
	public void testSignInWithValidUsernameAndInvalidPassword() {
		signInPage.enterUsername(ConfigReader.getProperty("username"));
		signInPage.enterPassword("invalidpassword");
		signInPage.clickLoginButton();
		String invalidMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
		System.out.println(invalidMessage);
		Assert.assertEquals(invalidMessage, "Invalid Username and Password");
	}

	@Test
	public void testSignInWithInvalidUsernameAndValidPassword() {
		signInPage.enterUsername("invalidusername");
		signInPage.enterPassword(ConfigReader.getProperty("password"));
		signInPage.clickLoginButton();
		String invalidMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
		System.out.println(invalidMessage);
		Assert.assertEquals(invalidMessage, "Invalid Username and Password");
	}

	@Test
	public void testSignInWithValidUsernameAndPassword() {
		signInPage.enterUsername(ConfigReader.getProperty("username"));
		signInPage.enterPassword(ConfigReader.getProperty("password"));
		signInPage.clickLoginButton();
		String actualMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
		Assert.assertEquals(actualMessage, "You are logged in");
	}

}
