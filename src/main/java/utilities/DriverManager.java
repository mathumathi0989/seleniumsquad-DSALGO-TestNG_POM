package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void initializeDriver(String browser) {
		if (driver.get() == null) {
			try {
				if (browser.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					driver.set(new ChromeDriver());
				} else if (browser.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver.set(new FirefoxDriver());
				} else {
					throw new IllegalArgumentException("Browser not supported: " + browser);
				}
				driver.get().manage().window().maximize();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to initialize WebDriver", e);
			}
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		WebDriver driverInstance = driver.get();
		if (driverInstance != null) {
			driverInstance.quit();
			driver.remove();
		}
	}
}
