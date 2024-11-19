package logout;

import com.kotan4ik.api.ApiMethods;
import com.kotan4ik.pom.AccountPage;
import com.kotan4ik.pom.LoginPage;
import com.kotan4ik.pom.MainPage;
import com.kotan4ik.utils.TestUtils;
import com.kotan4ik.webdriver.WebDriverFactory;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest {
    private static Map<String, String> testData;
    private WebDriver driver;
    private AccountPage accountPage;
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeAll
    public static void generatingRandomUser() {
        testData = TestUtils.generateRandomUserProperties();
    }

    @BeforeEach
    public void setup() {
        driver = WebDriverFactory.createWebDriver();
        ApiMethods.createUser(testData.get("email"), testData.get("password"), testData.get("name"));
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Positive test")
    @Description("Positive test. Authorized opening account page. After clicking logout button should redirect to login page. Opening account page also should redirect")
    public void positiveTestShouldRedirectToLoginPage() {
        loginPage.openPage();
        loginPage.login(testData.get("email"), testData.get("password"));
        mainPage.clickHeaderAccountButton();
        accountPage.clickLogoutButton();
        assertTrue(loginPage.isLoginPage(), "Expected redirection to login page");
        accountPage.openPage();
        assertFalse(accountPage.accountPageIsShown(), "Account page shouldn't be shown without authorization");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        ApiMethods.deleteUserByItsData(testData.get("name"), testData.get("email"), testData.get("password"));
    }
}
