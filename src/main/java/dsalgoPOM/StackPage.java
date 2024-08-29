package dsalgoPOM;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StackPage {

	WebDriver driver;
    WebDriverWait wait;
    public Alert alert;

    By stackTitle = By.xpath("//h4[text()='Stack']");
    By Implementationofstack = By.linkText("Implementation");
    By OperationStack = By.linkText("Operations in Stack");
    By applicationStack = By.linkText("Applications");
     private By practiceQuestionsLink = By.xpath("//a[text()='Practice Questions']"); // Updated locator strategy

    By tryhere = By.xpath("//a[@class='btn btn-info']");
    By RunButton = By.xpath("//button[@type='button']");
    By output = By.xpath("//pre[@id='output']");

    public StackPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickOperationsInStack() {
        WebElement element = driver.findElement(OperationStack);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void clickImplementation() {
        WebElement element = driver.findElement(Implementationofstack);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void clickApplications() {
        WebElement element = driver.findElement(applicationStack);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void clickPracticeQuestions() {
        WebElement practiceQuestions = wait.until(ExpectedConditions.elementToBeClickable(practiceQuestionsLink));
        practiceQuestions.click();
    }

    public void clickTryHere() {
        WebElement element = driver.findElement(tryhere);
        element.click();
    }

    public void enterCodeInTryHereEditor(String code) {
        String script = "var editor = document.querySelector('.CodeMirror').CodeMirror;"
                     + "editor.setValue(arguments[0]);";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, code);
    }

    public void clickRunButton() {
        WebElement runButton = driver.findElement(RunButton);
        runButton.click();
    }

    public String getOutputConsole() {
        return driver.findElement(output).getText();
    }

    public void handleOutput() {
        try {
            alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert message: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(output));
                String codeOutput = driver.findElement(output).getText();
                System.out.println("Code output: " + codeOutput);
            } catch (Exception ex) {
                System.out.println("No output element found or no output generated.");
            }
        }
    }
    
}
