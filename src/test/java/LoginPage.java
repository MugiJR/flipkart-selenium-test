import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.interactions.Action;		
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

class LoginPage extends PageBase{

    private By emailOrMobileInputBox = By.xpath("//span[contains(text(),'Enter Email/Mobile number')]/../../input");
    private By passwordInputBox = By.xpath("//span[contains(text(),'Enter Password')]/../../input");
    private By myProfileName = By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div[1]/div");
    private By loginButton = By.xpath("//button[@type='submit']/span[.='Login']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String mobileOrEmail, String password) {
        this.waitAndReturnElement(emailOrMobileInputBox).sendKeys(mobileOrEmail);
        this.waitAndReturnElement(passwordInputBox).sendKeys(password + "\n");
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
        return new DashboardPage(this.driver);
    }



}
