package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private static final By NAME_FIELD = By.xpath("//div[label[text()='Имя']]/input");
    private static final By EMAIL_FIELD = By.xpath("//div[label[text()='Email']]/input");
    private static final By PASSWORD_FIELD = By.xpath("//div[label[text()='Пароль']]/input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
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
}
