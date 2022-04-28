import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.*;


public class FlipKartSeleniumTest {

    private WebDriver driver;
    private String mobileNo = "8795398541";
    private String password = "passwordpassword";

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void validLoginTest() throws InterruptedException {  
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login(mobileNo, password);
        Thread.sleep(50000);
        // LOGIN BUTTON SHOULD NOT AVAILABLE AFTER SUCCESSFUL LOGIN
        Assert.assertFalse(dashboardPage.isLoginButtonAvailable());
    }

    @Test
    public void validLogoutTest() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login(mobileNo, password);
        loginPage.logout();
        this.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        // LOGIN BUTTON SHOULD AVAILABLE AFTER LOGOUT
        Assert.assertTrue(dashboardPage.isLoginButtonAvailable());
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
