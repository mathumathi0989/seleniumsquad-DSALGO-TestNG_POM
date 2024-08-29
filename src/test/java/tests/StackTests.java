package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dsalgoPOM.BaseTest;
import dsalgoPOM.HomePage;
import dsalgoPOM.SigninPage;
import dsalgoPOM.StackPage;
import utilities.ConfigReader;

public class StackTests extends BaseTest {
	private WebDriverWait wait;
	private SigninPage signInPage;
	private HomePage homepage;
	private StackPage stackPage;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
		super.setUp(browser); // Call the setup method from BaseTest
		// Initialize WebDriverWait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Initialize Page Objects
		homepage = new HomePage(driver);
		signInPage = new SigninPage(driver);
		stackPage = new StackPage(driver);

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
		signInPage.enterUsername(ConfigReader.getProperty("username"));
		signInPage.enterPassword(ConfigReader.getProperty("password"));
		signInPage.clickLoginButton();
	}

	@Test
	public void verifyUserCanNavigateToOperationsInStack() {
		homepage.selectStack();
		stackPage.clickOperationsInStack();
		String actual = driver.findElement(By.xpath("//p[text()='Operations in Stack']")).getText();
		Assert.assertEquals(actual, "Operations in Stack");
	}

	@Test
	public void verifyUserCanAccessTryEditorInOperationsInStack() {
		homepage.selectStack();
		stackPage.clickOperationsInStack();
		stackPage.clickTryHere();
		Assert.assertEquals(driver.getTitle(), "Assessment");
	}

	@Test
	public void verifyValidPythonCodeExecutionInStackTryEditor() {
		homepage.selectStack();
		stackPage.clickOperationsInStack();
		stackPage.clickTryHere();
		stackPage.enterCodeInTryHereEditor("print(\"hello\")");
		stackPage.clickRunButton();
		Assert.assertEquals(stackPage.getOutputConsole(), "hello");
	}

	@Test
	public void verifyInvalidPythonCodeExecutionInStackTryEditor() {
		homepage.selectStack();
		stackPage.clickOperationsInStack();
		stackPage.clickTryHere();
		stackPage.enterCodeInTryHereEditor("printf");
		stackPage.clickRunButton();
		stackPage.handleOutput();
		Assert.assertNotNull(stackPage.alert, "Alert is not present.");
	}

	@Test
	public void verifyUserCanNavigateToImplementationPage() {
		homepage.selectStack();
		stackPage.clickImplementation();
		String actual = driver.findElement(By.xpath("//p[text()='Implementation']")).getText();
		Assert.assertEquals(actual, "Implementation");
	}

	@Test
	public void verifyUserCanNavigateToApplicationsPage() {
		homepage.selectStack();
		stackPage.clickApplications();
		String actual = driver.findElement(By.xpath("//p[text()='Applications']")).getText();
		Assert.assertEquals(actual, "Applications");
	}

	@Test
	public void verifyUserCanNavigateToPracticeQuestionsPage() {
		homepage.selectStack();
		stackPage.clickOperationsInStack();
		stackPage.clickPracticeQuestions();
		Assert.assertEquals(driver.getTitle(), "Practice Questions");
	}

	@AfterMethod
	@Override
	public void tearDown() {
		super.tearDown(); // Call the teardown method from BaseTest
	}

}
