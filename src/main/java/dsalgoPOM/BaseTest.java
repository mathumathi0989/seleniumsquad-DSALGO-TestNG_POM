package dsalgoPOM;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utilities.ConfigReader;
import utilities.DriverManager;

public class BaseTest {

	public WebDriver driver;
	 @Parameters("browser")
	    @BeforeMethod
	    public void setUp(String browser) {
	        DriverManager.initializeDriver(browser);
	        driver = DriverManager.getDriver(); // Use class-level driver
	        driver.get(ConfigReader.getProperty("url"));
	    }

	    @AfterMethod
	    public void tearDown() {
	        DriverManager.quitDriver();
	    }
}
