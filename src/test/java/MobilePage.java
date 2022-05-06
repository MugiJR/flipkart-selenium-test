import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

class MobilePage extends Wishlist {

    private final By mobilePhone = By.xpath("//h1[text()='Mi Mobiles']//..//..//..//..//div[2]//div//div//div//a//div[1]//div[3]//div//*[local-name()='svg']//*[local-name()='path']");
    private final By myProfileName = By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div/div[1]/div");
    private final By electronicsMenuButton = By.xpath("//span[text()='Electronics']");
    
    public MobilePage(WebDriver driver) {
        super(driver);
    }

    public void selectMobileCompany() {
        Actions action = this.hoverOverElement(electronicsMenuButton);
        WebElement element = this.waitAndReturnElement(By.xpath("//a[@title='Mi']"));
        action.moveToElement(element);
        action.click().build().perform();
    }

    public void addMobileToWishList() {
        this.addToWishList(mobilePhone);
    }

    public int getMobileWishListCount() {
        try {
            return Integer.parseInt(this.getWishListCount(myProfileName).getText());
        } catch (TimeoutException ex) {
            return 0;
        }      
    }

}
