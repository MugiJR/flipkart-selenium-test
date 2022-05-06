import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


class MainPage extends PageBase {

    private final By loginMenuOpenButton = By.xpath("//a[text()='Login']");
    private final By loginMenuCloseBy = By.xpath("/html/body/div[2]/div/div/button");
    private final By footerBy = By.xpath("//footer/div/div[3]/div[2]/div/span/span");
    private final By searchBarTogglerBy = By.xpath("//input[@name='q']");

    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.flipkart.com/");
    }

    public LoginPage openLogin() {
        this.waitAndReturnElement(loginMenuOpenButton).click();
        return new LoginPage(this.driver); 
    }


    public void closeLoginPane() {
        try {
            this.waitAndReturnElement(loginMenuCloseBy).click();
        }
        catch (TimeoutException ex) {
            // No need to close anything
        }
    }
    

    public Boolean isLoginButtonAvailableInMainPage() {
        return this.waitAndReturnElement(loginMenuOpenButton).isDisplayed();
    }
    
     public SearchResultPage search(String searchQuery) {
         this.waitAndReturnElement(searchBarTogglerBy).sendKeys(searchQuery + Keys.ENTER);
         this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Filters']")));
         return new SearchResultPage(this.driver);
     }
}
