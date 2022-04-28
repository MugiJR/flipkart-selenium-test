import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

class DashboardPage extends PageBase {

    private By search = By.xpath("//input[@name='q']");
    private By loginMenuOpenButton = By.xpath("//a[text()='Login']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoginButtonAvailable() {
        boolean isLoginButtonExists = true;
        try {
            this.waitAndReturnElement(loginMenuOpenButton);   
        } catch (NoSuchElementException e) {
            isLoginButtonExists = false;
        } catch (TimeoutException e) {
            isLoginButtonExists = false;
        }
        return isLoginButtonExists;
    }
}
