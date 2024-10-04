package com.kotan4ik.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
  private static final String BROWSER_PROPERTY = "browser";
  private static final String DEFAULT_BROWSER = "chrome";
  private static final String WEBDRIVER_CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

  public static WebDriver createWebDriver() {
    Browser browser = getActiveBrowser();
    ConfigLoader configLoader = new ConfigLoader("webdriverconfig.properties");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    switch (browser) {
      case CHROME:
        System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, configLoader.getProperty("chromeDriverPath"));
        return new ChromeDriver(options);
      case YANDEX:
        System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, configLoader.getProperty("yandexDriverPath"));
        return new ChromeDriver(options);
      default:
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
  }

  private static Browser getActiveBrowser() {
    String browserName = System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER);
    return Browser.valueOf(browserName.toUpperCase());
  }
}
