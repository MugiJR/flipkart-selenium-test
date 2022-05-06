import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

class DashboardPage extends PageBase {

    private final By electronicsMenuButton = By.xpath("//span[text()='Electronics']");
    private final By appleMobile = By.xpath("//a[.='Apple']");
    private final By mobiles = By.xpath("//div[text()='Mobiles']//..");
    private final By myProfileName = By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div[1]/div");
    private final By logoutButton = By.xpath("//div[.='Logout']/../../a");

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
