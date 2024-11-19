package com.kotan4ik.pom;

import com.kotan4ik.utils.BurgerComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private static final By HEADER_ACCOUNT_BUTTON = By.xpath("//header//*[@href='/account']");
    private static final By ENTER_ACCOUNT_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By CONSTRUCT_BURGER_HEADER = By.xpath("//h1[text()='Соберите бургер']");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Opening main page")
    public void openPage() {
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Clicking account button in header")
    public void clickHeaderAccountButton() {
        driver.findElement(HEADER_ACCOUNT_BUTTON).click();
    }

    @Step("Clicking enter account button")
    public void clickEnterAccountButton() {
        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
    }

    @Step("Checking main page is shown")
    public boolean isShownMainPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CONSTRUCT_BURGER_HEADER));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicking on ingredient type {component} button")
    public void clickIngredientTypeButton(BurgerComponent component) {
        driver.findElement(component.getButtonLocator()).click();
    }

    @Step("Checking ingredient type {component} header is shown on screen")
    public boolean isVisibleIngredientTypeHeader(BurgerComponent component) {
        WebElement element = driver.findElement(component.getHeaderLocator());
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(
                                driver -> {
                                    Rectangle rect = element.getRect();
                                    Dimension windowSize = driver.manage().window().getSize();

                                    // условие, которое проверяем внутри явного ожидания
                                    return rect.getX() >= 0
                                            && rect.getY() >= 0
                                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                                });
    }
}
