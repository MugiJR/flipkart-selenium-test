import jdk.jfr.internal.tool.Main;
import org.junit.*;
import java.lang.Thread;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;


public class FlipKartSeleniumTest {

    private WebDriver driver;
    private final String mobileNo = "mugeshgutsy456@gmail.com";
    private final String password = "16864566";

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSearch() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        SearchResultPage searchResultPage = mainPage.search("Apple");
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("APPLE iPhone"));
    }

    @Test
    public void testSearchWithMultipleInput() {
        String[] searchQueries = {"apple mobile", "redmi", "samsung phone"};
        String[] searchResult = {"APPLE iPhone", "REDMI", "SAMSUNG"};
        for(int i = 0; i < searchQueries.length; i++) {
            String searchQuery = searchQueries[i];
            MainPage mainPage = new MainPage(this.driver);
            mainPage.closeLoginPane();
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            Assert.assertTrue(bodyText.contains(searchResult[i]));
        }


    }

    // TEST CASE - Reading the page title
    @Test
    public void testPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        String expectedFlipkartTitle = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
        String actualFlipkartTitle = this.driver.getTitle();
        System.out.println(actualFlipkartTitle);
        Assert.assertEquals(expectedFlipkartTitle, actualFlipkartTitle);
    }

    // TEST CASE - Login with valid credentials
    @Test
    public void validLoginTest() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login(mobileNo, password);
        Assert.assertTrue(dashboardPage.isMyProfileNameVisible());
    }

    // TEST CASE - Logout
    @Test
    public void validLogoutTest()  {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login(mobileNo, password);
        dashboardPage.logout();
        Assert.assertTrue(mainPage.isLoginButtonAvailableInMainPage());
    }

    // Form sending with user
    @Test
    public void addProductsToWishlist() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login(mobileNo, password);
        MobilePage mobilePage = dashboardPage.openMobileListPage();
        mobilePage.selectMobileCompany();
        mobilePage.addMobileToWishList();
        int totalCountInWishlist = mobilePage.getMobileWishListCount();
        System.out.println(totalCountInWishlist);
        Assert.assertEquals(1, totalCountInWishlist);
    }

    @Test()
    public void testHovering()  {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login(mobileNo, password);
        dashboardPage.doHover();
        String hoverText = dashboardPage.getHoverText();
        Assert.assertEquals(hoverText, "Apple");
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
