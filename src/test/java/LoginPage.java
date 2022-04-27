import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class LoginPage extends PageBase{

    private By emailOrMobileInputBox = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input");
    private By passwordInputBox = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String mobileOrEmail, String password) {
        this.waitAndReturnElement(emailOrMobileInputBox).sendKeys(mobileOrEmail);
        this.waitAndReturnElement(passwordInputBox).sendKeys(password + "\n");
        return new DashboardPage(this.driver);
    }
}
