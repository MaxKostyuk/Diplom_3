package main;

import com.kotan4ik.pom.MainPage;
import com.kotan4ik.utils.BurgerComponent;
import com.kotan4ik.webdriver.WebDriverFactory;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BurgerConstructorElementsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        driver = WebDriverFactory.createWebDriver();
        mainPage = new MainPage(driver);
    }

    @ParameterizedTest
    @EnumSource(BurgerComponent.class)
    @DisplayName("Test for scrolling ingredients with ingredient type button")
    @Description("Test for scrolling ingredients with ingredient type button. Clicking ingredient type button. List of ingredients should be scrolled to selected ingredient type header. isVisibleIngredientType method checks if header is shown in the window")
    public void positiveBurgerConstructorElementsTestShouldScrollToFirstElementOfKind(BurgerComponent component) throws InterruptedException {
        mainPage.openPage();
        if (BurgerComponent.BUN.equals(component))
            mainPage.clickIngredientTypeButton(BurgerComponent.FILLING); //Made for correct Bun test. By default bun header is shown
        mainPage.clickIngredientTypeButton(component);
        Thread.sleep(2000); //Made for headed mode. In headed mode page is not fully loaded by the time of the check
        assertTrue(mainPage.isVisibleIngredientTypeHeader(component));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
