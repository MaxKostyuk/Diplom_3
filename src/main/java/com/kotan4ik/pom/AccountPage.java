package com.kotan4ik.pom;

import io.qameta.allure.Step;
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
}
