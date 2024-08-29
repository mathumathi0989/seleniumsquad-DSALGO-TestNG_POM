package dsalgoPOM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased wait time for stability
	}

	// Locators
	private By registerButton = By.xpath("//a[normalize-space()='Register']");
	private By getStartedButton = By.xpath("//button[@class='btn']");
	private By dataStructuresDropDown = By.xpath("//a[@class='nav-link dropdown-toggle']");
	private By stackStart = By.xpath("//a[@href='stack']");
	private By queueStart = By.xpath("//a[@href='queue']");
	private By graphStart = By.xpath("//a[@href='graph']");
	private By arrayStart = By.xpath("//a[@href='array']");
	private By linkedListStart = By.xpath("//a[@href='linked-list']");
	private By treeStart = By.xpath("//a[@href='tree']");
	private By signOut = By.linkText("Sign out");
	private By dataStructureStart = By.xpath("//a[@href='data-structures-introduction']");

	// Actions
	public void clickGetStarted() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement getStarted = wait.until(ExpectedConditions.elementToBeClickable(getStartedButton));
		getStarted.click();

		
	}

	public void clickRegister() {
		driver.findElement(registerButton).click();
	}

	public void selectDataStructuresRequired() {
		String[] dsList = { "Arrays", "Linked List", "Stack", "Queue", "Tree", "Graph" };
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dataStructuresDropDown));
		dropdown.click();
		for (String dsSelect : dsList) {
			WebElement option = driver.findElement(By.xpath("//a[normalize-space()='" + dsSelect + "']"));
			option.click();
		}
	}

	public void selectDataStructuresModule() {
		String[] ds = { "data-structures-introduction", "array", "linked-list", "stack", "queue", "tree", "graph" };
		for (String select : ds) {
			WebElement element = driver.findElement(By.xpath("//a[@href='" + select + "']"));
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
			element.click();
		}
	}

	public void selectStack() {
		WebElement element = driver.findElement(stackStart);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void selectQueue() {
		WebElement element = driver.findElement(queueStart);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void selectGraph() {
		WebElement element = driver.findElement(graphStart);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void selectTree() {
		WebElement element = driver.findElement(treeStart);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void selectArray() {
		WebElement element = driver.findElement(arrayStart);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void selectLinkedList() {
		WebElement element = driver.findElement(linkedListStart);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void selectDataStructure() {
		WebElement element = driver.findElement(dataStructureStart);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void clickSignOutButton() {
		WebElement element = driver.findElement(signOut);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

}
