package ru.yandex.praktikum.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static ru.yandex.praktikum.utils.DriverConfig.*;

public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty(BROWSER, DEFAULT_BROWSER);

            if (FIREFOX_BROWSER.equalsIgnoreCase(browser)) {
                driver = new FirefoxDriver();
            } else {
                driver = new ChromeDriver();
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
            driver.manage().window().maximize();
            driver.get(BASE_URL);
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}