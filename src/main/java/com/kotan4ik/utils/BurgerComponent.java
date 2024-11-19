package com.kotan4ik.utils;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public enum BurgerComponent {
    BUN(By.xpath("//span[text()='Булки']"), By.xpath("//h2[text()='Булки']")),
    SAUCE(By.xpath("//span[text()='Соусы']"), By.xpath("//h2[text()='Соусы']")),
    FILLING(By.xpath("//span[text()='Начинки']"), By.xpath("//h2[text()='Начинки']"));
    private final By buttonLocator;
    private final By headerLocator;


    BurgerComponent(By buttonLocator, By headerLocator) {
        this.buttonLocator = buttonLocator;
        this.headerLocator = headerLocator;
    }
}
