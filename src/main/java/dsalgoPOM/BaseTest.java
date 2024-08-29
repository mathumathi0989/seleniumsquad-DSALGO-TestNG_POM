package dsalgoPOM;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utilities.ConfigReader;
import utilities.DriverManager;

public class BaseTest {

	protected WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
	    DriverManager.initializeDriver(browser);
	    driver = DriverManager.getDriver();
	}

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverManager.quitDriver();
        }
    }

}
