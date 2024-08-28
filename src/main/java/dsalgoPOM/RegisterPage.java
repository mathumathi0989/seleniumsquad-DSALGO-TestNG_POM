package dsalgoPOM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

	private WebDriver driver;
	WebDriverWait wait;
    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By usernameField = By.xpath("//input[@id='id_username']");
    private By passwordField = By.xpath("//input[@id='id_password1']");
    private By confirmPasswordField = By.xpath("//input[@id='id_password2']");
    private By registerButton = By.xpath("//a[normalize-space()='Register']");


	
	
    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    
    public void clickRegister() {
		WebElement registerButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Register']")));
		registerButton.click();
	}
    
    
}
