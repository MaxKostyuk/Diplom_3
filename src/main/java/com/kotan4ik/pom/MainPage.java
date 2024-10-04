package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private static final By HEADER_ACCOUNT_BUTTON = By.xpath("//header//*[@href='/account']");
    private static final By ENTER_ACCOUNT_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    public static final By CONSTRUCT_BURGER_HEADER = By.xpath("//h1[text()='Соберите бургер']");
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
}
