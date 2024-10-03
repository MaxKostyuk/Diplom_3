package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private static final String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    public static final By ENTER_LINK = By.xpath("//a[@href='/login']");
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Opening forgot password page")
    public void openPage() {
        driver.get(FORGOT_PASSWORD_URL);
    }

    @Step("Clicking on enter link")
    public void clickEnterLink() {
        driver.findElement(ENTER_LINK).click();
    }
}
