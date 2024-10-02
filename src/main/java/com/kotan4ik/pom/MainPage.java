package com.kotan4ik.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private static final By HEADER_ACCOUNT_BUTTON = By.xpath("//header//*[@href='/account']");
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


}
