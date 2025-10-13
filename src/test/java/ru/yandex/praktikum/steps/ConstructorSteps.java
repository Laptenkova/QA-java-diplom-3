package ru.yandex.praktikum.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pageobject.MainPage;

import java.time.Duration;
import java.util.logging.Logger;

public class ConstructorSteps {
    private final MainPage mainPage;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(ConstructorSteps.class.getName());

    public ConstructorSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickBunsSection() {
        logger.info("Клик по разделу Булки");
        mainPage.clickBunsSection();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(mainPage.getCurrentSectionLocator(), "Булки"));
    }

    public void clickSaucesSection() {
        logger.info("Клик по разделу Соусы");
        mainPage.clickSaucesSection();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(mainPage.getCurrentSectionLocator(), "Соусы"));
    }

    public void clickFillingsSection() {
        logger.info("Клик по разделу Начинки");
        mainPage.clickFillingsSection();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(mainPage.getCurrentSectionLocator(), "Начинки"));
    }
}
