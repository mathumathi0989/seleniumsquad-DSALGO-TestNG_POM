package dsalgoPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	 private WebDriver driver;

	    // Constructor
	    public HomePage(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Locators
	    private By registerButton = By.xpath("//a[normalize-space()='Register']");
private By getStartedButton = By.xpath("//button[@class='btn']");

	    // Actions


public void clickGetStarted() {
    driver.findElement(getStartedButton).click();
}
	    public void clickRegister() {
	        driver.findElement(registerButton).click();
	    }
	    
}
