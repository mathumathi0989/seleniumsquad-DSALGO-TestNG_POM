package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dsalgoPOM.BaseTest;
import dsalgoPOM.HomePage;
import dsalgoPOM.RegisterPage;
import utilities.ExcelUtil;

public class RegisterTests extends BaseTest {

	private WebDriverWait wait;
	
	@BeforeMethod
    public void setUpWait() {
        // Initialize WebDriverWait after driver is set up
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	
	@DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        String excelPath = "src/test/resources/registrationData.xlsx";
        String sheetName = "Sheet1";
        
        ExcelUtil.loadExcel(excelPath, sheetName);
        
        int rows = ExcelUtil.getRowCount();
        
        Object[][] data = new Object[rows][3]; // Assuming three columns: URL, Username, Password

        for (int i = 0; i < rows; i++) {
            data[i][0] = ExcelUtil.getCellData(i + 1, 0); // URL
            data[i][1] = ExcelUtil.getCellData(i + 1, 1); // Username
            data[i][2] = ExcelUtil.getCellData(i + 1, 2); // Password
        }
        
        ExcelUtil.closeWorkbook();
        
        return data;
    }

    @Test(dataProvider = "registrationData")
    public void testUserRegistration(String url, String username, String password) {
        // Initialize Page Objects
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        // Navigate to the given URL
        driver.get(url);
        
        // Navigate to the Register Page
        homePage.clickGetStarted();
        homePage.clickRegister();
       
        // Perform registration
        registerPage.enterUsername(username);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(password);
        registerPage.clickRegister();
        
        // Wait for the success message and verify
        String actualMessage = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']"))).getText();
        System.out.println(actualMessage);
        String expectedMessage = "New Account Created. You are logged in as " + username;
        Assert.assertEquals(actualMessage, expectedMessage);
        
        // Verify the title of the page
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "NumpyNinja");
    }
	
}
