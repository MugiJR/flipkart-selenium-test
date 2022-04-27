import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class DashboardPage extends PageBase {

    private By loggedInUserName = By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div/div");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getLoggedInUserName() {
        return this.waitAndReturnElement(loggedInUserName).getText();
    }
}
