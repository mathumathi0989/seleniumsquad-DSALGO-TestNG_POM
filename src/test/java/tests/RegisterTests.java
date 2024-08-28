package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dsalgoPOM.BaseTest;
import dsalgoPOM.HomePage;
import dsalgoPOM.RegisterPage;
import utilities.ConfigReader;

public class RegisterTests extends BaseTest {

	private WebDriverWait wait;

	@BeforeMethod
	public void setUpWait() {
	    // Initialize WebDriverWait after driver is set up
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@Test
	public void testUserRegistration() {
	    // Initialize Page Objects
	    HomePage homePage = new HomePage(driver);
	    RegisterPage registerPage = new RegisterPage(driver);

	    // Navigate to the Register Page
	    homePage.clickGetStarted();
	    homePage.clickRegister();
	   
	    // Perform registration
	    registerPage.enterUsername(ConfigReader.getProperty("regusername"));
	    registerPage.enterPassword(ConfigReader.getProperty("regpassword"));
	    registerPage.enterConfirmPassword(ConfigReader.getProperty("regconfirmpassword"));
	    registerPage.clickRegister();
	    
	    // Wait for the success message and verify
	    String actualMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
	    System.out.println(actualMessage);
	    String expectedMessage = "New Account Created. You are logged in as " + ConfigReader.getProperty("regusername");
	    Assert.assertEquals(actualMessage, expectedMessage);
	    
	    // Verify the title of the page
	    String actualTitle = driver.getTitle();
	    Assert.assertEquals(actualTitle, "NumpyNinja");
	}
	
}
