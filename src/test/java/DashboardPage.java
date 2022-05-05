import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

class DashboardPage extends PageBase {

    private By search = By.xpath("//input[@name='q']");
    private By loginMenuOpenButton = By.xpath("//a[text()='Login']");
    private By electronicsMenuButton = By.xpath("//span[text()='Electronics']");
    private By hover = By.xpath("//img[@title='Flipkart']");
    private By appleMobile = By.xpath("//a[.='Apple']");
    private By mobiles = By.xpath("//div[text()='Mobiles']//..");
    private By myProfileName = By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div[1]/div");
    private By logoutButton = By.xpath("//div[.='Logout']/../../a");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isMyProfileNameVisible() {
        return this.waitAndReturnElement(myProfileName).isDisplayed();
    }

    public MobilePage openMobileListPage() {
        this.waitAndReturnElement(mobiles).click();
        this.waitUntilElementInvisible(mobiles);
        return new MobilePage(this.driver);

    } 

    public void doHover() {
        this.waitAndReturnElement(mobiles).click();
        this.waitUntilElementInvisible(mobiles);
        this.hoverOverElement(electronicsMenuButton);
    }

    public void logout() {
        Actions builder = this.hoverOverElement(myProfileName);
        WebElement logoutButtonElement = this.waitAndReturnElement(logoutButton);
        builder.moveToElement(logoutButtonElement);
        builder.click().build().perform();
        //this.wait.until(ExpectedConditions.invisibilityOfElementLocated(logoutButton));
        this.waitUntilElementInvisible(myProfileName);
    }


    public String getHoverText() {
        return getText(appleMobile);
    }


}
