package account;

import com.kotan4ik.api.ApiMethods;
import com.kotan4ik.pom.AccountPage;
import com.kotan4ik.utils.TestUtils;
import com.kotan4ik.webdriver.WebDriverFactory;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {
    private static Map<String, String> testData;
    private WebDriver driver;
    private String token;
    private AccountPage accountPage;

    @BeforeAll
    public static void generatingRandomUser() {
        testData = TestUtils.generateRandomUserProperties();
    }

    @BeforeEach
    public void setup() {
        driver = WebDriverFactory.createWebDriver();
        ApiMethods.createUser(testData.get("email"), testData.get("password"), testData.get("name"));
        Response response = ApiMethods.loginUser(testData.get("email"), testData.get("password"), testData.get("name"));
        token = ApiMethods.getTokenFromResponse(response);
        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Positive test")
    @Description("Positive test with authorization. Should open account page")
    public void positiveTest() {
        accountPage.openPage();
        accountPage.openPage(token);
        assertTrue(accountPage.accountPageIsShown(), "Expected account page is not shown");
    }

    @Test
    @DisplayName("Negative test")
    @Description("Negative test without authorization. Should redirect to an other page")
    public void negativeTestShouldRedirect() {
        accountPage.openPage();
        assertFalse(accountPage.accountPageIsShown(), "Expected redirection to an other page");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        ApiMethods.deleteUser(token);
    }
}
