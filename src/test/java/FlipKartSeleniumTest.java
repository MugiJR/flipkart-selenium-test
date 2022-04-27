import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.*;


public class FlipKartSeleniumTest {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void validLoginTest() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login("", "");
        loginPage.logout();
        this.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        System.out.println(mainPage.loginMenuOpenButtonText());
        
        
        //Assert.assertEquals(dashboardPage.getLoggedInUserName(), "Mugesh");
     
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
