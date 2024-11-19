package account;

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

public class AccountTest {
    private static Map<String, String> testData;
    private WebDriver driver;
    private AccountPage accountPage;
    private MainPage mainPage;

    @BeforeAll
    public static void generatingRandomUser() {
        testData = TestUtils.generateRandomUserProperties();
    }

    @BeforeEach
    public void setup() {
        driver = WebDriverFactory.createWebDriver();
        ApiMethods.createUser(testData.get("email"), testData.get("password"), testData.get("name"));
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Positive test")
    @Description("Positive test with authorization. Should open account page")
    public void positiveTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(testData.get("email"), testData.get("password"));
        mainPage.clickHeaderAccountButton();

        assertTrue(accountPage.accountPageIsShown(), "Expected account page is not shown");
    }

    @Test
    @DisplayName("Negative test")
    @Description("Negative test without authorization. Should redirect to an other page")
    public void negativeTestShouldRedirect() {
        mainPage.openPage();
        mainPage.clickHeaderAccountButton();

        assertFalse(accountPage.accountPageIsShown(), "Expected redirection to an other page");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        ApiMethods.deleteUserByItsData(testData.get("name"), testData.get("email"), testData.get("password"));
    }
}
