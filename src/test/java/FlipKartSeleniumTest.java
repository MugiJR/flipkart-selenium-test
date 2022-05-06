import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FlipKartSeleniumTest  {

    private WebDriver driver;
    private final String mobileNo = "lamiviw368@3dmasti.com";
    private final String password = "passwordpassword";

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
        String actualFlipkartTitle = mainPage.getPageTitle();
        Assert.assertEquals(expectedFlipkartTitle, actualFlipkartTitle);
    }

    // TEST CASE - Login with valid credentials
    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login(mobileNo, password);
        Assert.assertTrue(dashboardPage.isMyProfileNameVisible());
    }

    // TEST CASE - Logout
    @Test
    public void testLogout()  {
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
        DashboardPage dashboardPage = mainPage.openDashboard();
        dashboardPage.doHover();
        String hoverText = dashboardPage.getHoverText();
        Assert.assertEquals(hoverText, "Apple");
    }

    @Test()
    public void testBrowserBackButton() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        SearchResultPage searchResultPage;
        String bodyText;
        searchResultPage = mainPage.search("Samsung Galaxy Mobile");
        bodyText = searchResultPage.getBodyText();
        Assert.assertFalse(bodyText.contains("APPLE iPhone"));
        searchResultPage.goBack();
        searchResultPage = mainPage.search("apple");
        bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("APPLE iPhone"));
    }

    @Test
    public void testDropdown(){
        MainPage mainPage = new MainPage(this.driver);
        mainPage.closeLoginPane();
        DashboardPage dashboardPage = mainPage.openDashboard();
        MobilePage mobilePage = dashboardPage.openMobileListPage();
        mobilePage.selectMobileCompany();
        Assert.assertTrue(mobilePage.getFirstValueFromPriceDropdown().contains("2000"));
    }

    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
