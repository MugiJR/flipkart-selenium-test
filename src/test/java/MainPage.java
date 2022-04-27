import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class MainPage extends PageBase {

    private By loginMenuOpenButton = By.xpath("//a[text()='Login']");
    private By loginMenuCloseBy = By.xpath("/html/body/div[2]/div/div/button");
    private By footerBy = By.xpath("//footer/div/div[3]/div[2]/div/span/span");
    private By emailInputBox = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input");
    // private By searchBarTogglerBy = By.xpath("//a[@class='search-bar-toggler']/i");
    // private By searchBarBy = By.name("search");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.flipkart.com/");
    }

    public LoginPage openLogin() {
        this.waitAndReturnElement(loginMenuOpenButton).click();
        return new LoginPage(this.driver); 
    }

    public String loginMenuOpenButtonText() {
        return this.waitAndReturnElement(loginMenuOpenButton).getText();

    }
    
    
    public void closeLoginPane() {
        this.waitAndReturnElement(loginMenuCloseBy).click();
    }
    
    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }
    
    // public SearchResultPage search(String searchQuery) {
    //     this.waitAndReturnElement(searchBarTogglerBy).click();
        
    //     this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
    //     return new SearchResultPage(this.driver);
    // }
}
