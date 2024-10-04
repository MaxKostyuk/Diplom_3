package login;

import com.kotan4ik.api.ApiMethods;
import com.kotan4ik.pom.*;
import com.kotan4ik.utils.TestUtils;
import com.kotan4ik.webdriver.WebDriverFactory;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private static Map<String, String> testData;
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    @BeforeAll
    public static void generatingRandomUser() {
        testData = TestUtils.generateRandomUserProperties();
    }

    @BeforeEach
    public void setUp() {
        ApiMethods.createUser(testData.get("email"), testData.get("password"), testData.get("name"));
        driver = WebDriverFactory.createWebDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Positive login test using account button in header")
    @Description("Positive login test with header account button. Pushing account button in header to login. After login should return to main page. Pushing account button after authorization should open account page")
    public void positiveLoginTestUsingHeaderAccountButton() {
        mainPage.openPage();
        mainPage.clickHeaderAccountButton();
        loginPage.sendEmailValue(testData.get("email"));
        loginPage.sendPasswordValue(testData.get("password"));
        loginPage.pushEnterButton();
        mainPage.clickHeaderAccountButton();
        assertTrue(accountPage.accountPageIsShown(), "Expected account page is not shown");
    }

    @Test
    @DisplayName("Positive login test using enter account button on main page")
    @Description("Positive login test with enter account button on main page. Pushing enter account button to login. After login should return to main page. Pushing account button after authorization should open account page")
    public void positiveLoginTestMainPageEnterAccountButton() {
        mainPage.openPage();
        mainPage.clickEnterAccountButton();
        loginPage.sendEmailValue(testData.get("email"));
        loginPage.sendPasswordValue(testData.get("password"));
        loginPage.pushEnterButton();
        mainPage.clickHeaderAccountButton();
        assertTrue(accountPage.accountPageIsShown(), "Expected account page is not shown");
    }

    @Test
    @DisplayName("Positive login test with register page")
    @Description("Positive login test with register page. Opening register page, clicking enter link. Filling login fields, pushing login button. After logging in should open main page. Clicking on account button in header should open account page")
    public void positiveLoginTestWithRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.openPage();
        registerPage.clickEnterLink();
        loginPage.sendEmailValue(testData.get("email"));
        loginPage.sendPasswordValue(testData.get("password"));
        loginPage.pushEnterButton();
        mainPage.clickHeaderAccountButton();
        assertTrue(accountPage.accountPageIsShown(), "Expected account page is not shown");
    }

    @Test
    @DisplayName("Positive login test with forgot password page")
    @Description("Positive login test with forgot password page. Opening forgot password page, clicking enter link. Filling login fields, pushing login button. After logging in should open main page. Clicking on account button in header should open account page")
    public void positiveLoginTestWithForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        forgotPasswordPage.openPage();
        forgotPasswordPage.clickEnterLink();
        loginPage.sendEmailValue(testData.get("email"));
        loginPage.sendPasswordValue(testData.get("password"));
        loginPage.pushEnterButton();
        mainPage.clickHeaderAccountButton();
        assertTrue(accountPage.accountPageIsShown(), "Expected account page is not shown");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        ApiMethods.deleteUserByItsData(testData.get("name"), testData.get("email"), testData.get("password"));
    }
}
