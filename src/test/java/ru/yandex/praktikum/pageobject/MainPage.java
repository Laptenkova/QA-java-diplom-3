package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object для главной страницы приложения
 * Содержит методы для взаимодействия с элементами главной страницы
 */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By loginAccountButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");
    private final By personalAccountButton = By.cssSelector("a[href='/account'] p");
    private final By bunsSection = By.xpath("//span[text()='Булки']");
    private final By saucesSection = By.xpath("//span[text()='Соусы']");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']");
    private final By currentSection = By.cssSelector("div.tab_tab_type_current__2BEPc");
    private final By mainPageHeader = By.xpath("//h1[text()='Соберите бургер']");

    /**
     * Конструктор инициализирует драйвер и ожидание
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Кликает на элемент после ожидания его кликабельности
     *
     * @param locator локатор элемента для клика
     */
    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Получает текст элемента после ожидания его видимости
     *
     * @param locator локатор элемента для получения текста
     * @return текст элемента
     */
    private String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Проверяет отображение элемента на странице
     *
     * @param locator локатор элемента для проверки
     * @return true если элемент отображается, false в противном случае
     */
    private boolean isDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    /**
     * Нажимает кнопку входа в аккаунт
     *
     * @return Page Object страницы авторизации
     */
    @Step("Нажатие кнопки 'Войти в аккаунт'")
    public LoginPage clickLoginAccountButton() {
        clickElement(loginAccountButton);
        return new LoginPage(driver);
    }

    /**
     * Нажимает кнопку личного кабинета
     *
     * @return Page Object страницы авторизации
     */
    @Step("Нажатие кнопки 'Личный кабинет'")
    public LoginPage clickPersonalAccountButton() {
        clickElement(personalAccountButton);
        return new LoginPage(driver);
    }

    /**
     * Нажимает раздел "Булки" в конструкторе бургеров
     * Активирует секцию с булками для выбора
     */
    @Step("Нажатие раздела 'Булки'")
    public void clickBunsSection() {
        clickElement(bunsSection);
    }

    /**
     * Нажимает раздел "Соусы" в конструкторе бургеров
     * Активирует секцию с соусами для выбора
     */
    @Step("Нажатие раздела 'Соусы'")
    public void clickSaucesSection() {
        clickElement(saucesSection);
    }

    /**
     * Нажимает раздел "Начинки" в конструкторе бургеров
     * Активирует секцию с начинками для выбора
     */
    @Step("Нажатие раздела 'Начинки'")
    public void clickFillingsSection() {
        clickElement(fillingsSection);
    }

    /**
     * Получает текст активного раздела конструктора
     *
     * @return название активного раздела (Булки/Соусы/Начинки)
     */
    @Step("Получение текста активного раздела")
    public String getCurrentSectionText() {
        return getText(currentSection);
    }

    /**
     * Проверяет загрузку главной страницы
     *
     * @return true если главная страница загружена
     */
    @Step("Проверка загрузки главной страницы")
    public boolean isMainPageLoaded() {
        return isDisplayed(mainPageHeader);
    }
}