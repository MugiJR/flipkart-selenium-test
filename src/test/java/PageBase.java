import org.junit.*;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,  40);
    }

    public String getText(By locator) {
        String displayedText = this.waitAndReturnElement(locator).getText();
        if (displayedText.isEmpty()) {
            return this.waitAndReturnElement(locator).getAttribute("value");
        } else {
            return displayedText;
        }
    }

    
    protected WebElement waitAndReturnElement(By locator) throws NoSuchElementException, TimeoutException {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected void waitUntilElementInvisible(By locator) {
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    
    protected Actions hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return actions;
    }

    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

    protected void goBack() {
        driver.navigate().back();
    }



}
