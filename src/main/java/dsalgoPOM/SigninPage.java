package dsalgoPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SigninPage {
	public WebDriver driver;

    private By signIn = By.xpath("//a[normalize-space()='Sign in']");
    private By passwordField = By.xpath("//input[@id='id_password']");
    private By userNameField = By.xpath("//input[@id='id_username']");
    private By loginButton = By.xpath("//input[@value='Login']");

    public SigninPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(userNameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    
    
}
