package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private static final String ACCOUNT_PAGE_URL = "https://stellarburgers.nomoreparties.site/account";
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Checking current page is account page")
    public boolean isAccountPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            return wait.until(ExpectedConditions.urlToBe(ACCOUNT_PAGE_URL));
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Opening account page")
    public void openPage() {
        driver.get(ACCOUNT_PAGE_URL);
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
}
