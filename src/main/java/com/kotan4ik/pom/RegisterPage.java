package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private static final By NAME_FIELD = By.xpath("//div[label[text()='Имя']]/input");
    private static final By EMAIL_FIELD = By.xpath("//div[label[text()='Email']]/input");
    private static final By PASSWORD_FIELD = By.xpath("//div[label[text()='Пароль']]/input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By INCORRECT_PASSWORD_MESSAGE = By.xpath("//p[text()='Некорректный пароль']");
    private static final By ENTER_LINK = By.xpath("//a[@href='/login']");
    private static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Opening register page")
    public void openPage() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Filling name field with value {name}")
    public void sendNameValue(String name) {
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    @Step("Filling email field with value {email}")
    public void sendEmailValue(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Filling password field with value {password}")
    public void sendPasswordValue(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Clicking on register button")
    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Checking incorrect password message is shown")
    public boolean isVisibleIncorrectPasswordMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD_MESSAGE));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Clicking enter link")
    public void clickEnterLink() {
        driver.findElement(ENTER_LINK).click();
    }
}
