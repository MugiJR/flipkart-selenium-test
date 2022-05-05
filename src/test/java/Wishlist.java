import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;	
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;

public class Wishlist extends PageBase {

    public Wishlist(WebDriver driver) {
        super(driver);
    }

    public void addToWishList(By locator) {
        this.waitAndReturnElement(locator).click();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebElement getWishListCount(By locator) {
        WebElement myProfile = this.waitAndReturnElement(locator);
        Actions action = new Actions(this.driver);
        action.moveToElement(myProfile).build().perform();
        return this.waitAndReturnElement(By.xpath("//div[text()='Wishlist']/../div[2]"));
    }

    
}
