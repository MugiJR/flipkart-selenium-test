import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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

    protected String getPageTitle() {
        return driver.getTitle();
    }



}
