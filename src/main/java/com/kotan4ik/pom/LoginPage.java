package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private static final By REGISTER_LINK = By.xpath("//*[@href='/register']");
    private static final By EMAIL_FIELD = By.xpath("//div[label[text()='Email']]/input");
    private static final By PASSWORD_FIELD = By.xpath("//div[label[text()='Пароль']]/input");
    private static final By ENTER_BUTTON = By.xpath("//button[text()='Войти']");
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Clicking on register link")
    public void clickRegisterLink() {
        driver.findElement(REGISTER_LINK).click();
    }

    @Step("Checking current page is login page")
    public boolean isLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            return wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Filling email field with value {email}")
    public void sendEmailValue(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Filling password field with value {password}")
    public void sendPasswordValue(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Pushing enter button to submit login")
    public void pushEnterButton() {
        driver.findElement(ENTER_BUTTON).click();
    }
}
