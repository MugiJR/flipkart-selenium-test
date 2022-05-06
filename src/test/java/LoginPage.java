import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

class LoginPage extends PageBase{

    private final By emailOrMobileInputBox = By.xpath("//span[contains(text(),'Enter Email/Mobile number')]/../../input");
    private final By passwordInputBox = By.xpath("//span[contains(text(),'Enter Password')]/../../input");
    private final By loginButton = By.xpath("//button[@type='submit']/span[.='Login']");

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
