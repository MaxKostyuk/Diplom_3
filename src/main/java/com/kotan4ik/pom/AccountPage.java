package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private static final String ACCOUNT_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    public static final By EXIT_BUTTON = By.xpath("//button[text()='Выход']");
    public static final By HEADER_LOGO = By.className("AppHeader_header__logo__2D0X2");
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Checking account page is shown")
    public boolean accountPageIsShown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(EXIT_BUTTON));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Opening account page")
    public void openPage() {
        driver.get(ACCOUNT_PAGE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(ACCOUNT_PAGE_URL));
    }

    @Step("Putting token to local storage")
    private void addToken(String token) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('accessToken', 'Bearer " + token + "');");
    }

    @Step("Opening page with authorization token {token}")
    public void openPage(String token) {
        addToken(token);
        openPage();
    }

    @Step("Clicking logout button")
    public void clickLogoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(EXIT_BUTTON));
        driver.findElement(EXIT_BUTTON).click();
    }

    @Step("Clicking on header logo")
    public void clickHeaderLogo() {
        driver.findElement(HEADER_LOGO).click();
    }
}
