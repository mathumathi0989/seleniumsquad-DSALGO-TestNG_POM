package dsalgoPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

	private WebDriver driver;

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By usernameField = By.xpath("//input[@id='id_username']");
    private By passwordField = By.xpath("//input[@id='id_password1']");
    private By confirmPasswordField = By.xpath("//input[@id='id_password2']");
    private By registerButton = By.xpath("//input[@value='Register']");


	
	
    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }
    
}
