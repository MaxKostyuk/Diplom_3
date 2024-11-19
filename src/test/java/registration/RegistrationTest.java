package registration;

import com.kotan4ik.api.ApiMethods;
import com.kotan4ik.pom.LoginPage;
import com.kotan4ik.pom.MainPage;
import com.kotan4ik.pom.RegisterPage;
import com.kotan4ik.utils.TestUtils;
import com.kotan4ik.webdriver.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("User registration")
public class RegistrationTest {
    public static final String INVALID_PASSWORD = "12345";
    private static Map<String, String> testData;
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeAll
    public static void generatingRandomUser() {
        testData = TestUtils.generateRandomUserProperties();
    }

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createWebDriver();
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Positive test for user registration")
    @Description("Positive test. Opening main page, click on login button, click register link, filling data, clicking on register button. After registration should open login page")
    public void positiveTestRegisterUserShouldReturnLoginPage() {
        mainPage.openPage();
        mainPage.clickHeaderAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendNameValue(testData.get("name"));
        registerPage.sendEmailValue(testData.get("email"));
        registerPage.sendPasswordValue(testData.get("password"));
        registerPage.clickRegisterButton();
        assertTrue(loginPage.isLoginPage(), "Page is not expected login page");
    }

    @Test
    @DisplayName("Negative test for user register with incorrect password")
    @Description("Negative test with incorrect password. Opening main page, click on login button, click register link, filling data, clicking on register button. Should stay on register page and show incorrect password message")
    public void negativeTestWithIncorrectPasswordShouldShowErrorMessage() {
        mainPage.openPage();
        mainPage.clickHeaderAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendNameValue(testData.get("name"));
        registerPage.sendEmailValue(testData.get("email"));
        registerPage.sendPasswordValue(INVALID_PASSWORD);
        registerPage.clickRegisterButton();
        assertTrue(registerPage.isVisibleIncorrectPasswordMessage(), "Expected incorrect password message on register page not found");
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
        if (ApiMethods.userExists(testData.get("name"), testData.get("email"), testData.get("password")))
            ApiMethods.deleteUserByItsData(testData.get("name"), testData.get("email"), testData.get("password"));
    }
}
