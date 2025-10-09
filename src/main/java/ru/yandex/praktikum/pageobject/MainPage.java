package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object для главной страницы Stellar Burgers
 * Содержит элементы для навигации и входа в систему
 */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для кнопок входа
    private final By loginAccountButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");
    private final By personalAccountButton = By.cssSelector("a[href='/account'] p");

    // Локаторы разделов конструктора
    private final By bunsSection = By.xpath("//h2[text()='Булки']");
    private final By saucesSection = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSection = By.xpath("//h2[text()='Начинки']"); // ← ИСПРАВЛЕНО

    // Локатор для проверки активного раздела
    private final By currentSection = By.cssSelector("div.tab_tab_type_current__2BEPc");

    /**
     * Конструктор главной страницы
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Кликает на кнопку "Войти в аккаунт" на главной странице
     */
    public LoginPage clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton)).click();
        return new LoginPage(driver);
    }

    /**
     * Кликает на кнопку "Личный Кабинет" в хедере
     */
    public LoginPage clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
        return new LoginPage(driver);
    }

    /**
     * Кликает на раздел "Булки" в конструкторе
     */
    public void clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
    }

    /**
     * Кликает на раздел "Соусы" в конструкторе
     */
    public void clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
    }

    /**
     * Кликает на раздел "Начинки" в конструкторе
     */
    public void clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
    }

    /**
     * Получает текст активного раздела в конструкторе
     */
    public String getCurrentSectionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentSection)).getText();
    }

    /**
     * Проверяет, что главная страница загружена
     */
    public boolean isMainPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginAccountButton)).isDisplayed();
    }
}