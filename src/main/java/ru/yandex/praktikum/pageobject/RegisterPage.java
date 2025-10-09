package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.constants.TestConstants;

import java.time.Duration;

/**
 * Page Object для страницы регистрации Stellar Burgers
 * Содержит элементы формы регистрации и методы для работы с ними
 */
public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы формы регистрации
    private final By nameField = By.xpath("(//input[@name='name'])[1]");
    private final By emailField = By.xpath("(//input[@name='name'])[2]");
    private final By passwordField = By.cssSelector("input[name='Пароль']");

    // Локатор кнопки регистрации
    private final By registerButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");

    // Локатор ссылки "Войти"
    private final By loginLink = By.cssSelector("a[href='/login']");

    // Локатор для ошибок пароля
    private final By passwordError = By.xpath(".//p[contains(@class, 'input__error')]");

    // Локатор для успешной регистрации (перенаправление на логин)
    private final By loginPageIndicator = By.cssSelector("button.button_button__33qZ0");

    /**
     * Конструктор страницы регистрации
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Вводит имя в поле ввода
     */
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    /**
     * Вводит email в поле ввода
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    /**
     * Вводит пароль в поле ввода
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    /**
     * Кликает на кнопку "Зарегистрироваться"
     */
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    /**
     * Кликает на ссылку "Войти"
     */
    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }

    /**
     * Получает текст ошибки пароля
     */
    public String getPasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    /**
     * Проверяет, что регистрация успешна (перенаправление на страницу входа)
     */
    public boolean isRegistrationSuccessful() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageIndicator)).isDisplayed();
    }

    /**
     * Проверяет, что страница регистрации загружена
     */
    public boolean isRegisterPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).isDisplayed();
    }

    /**
     * Выполняет успешную регистрацию с валидными данными
     */
    public void registerWithValidData() {
        enterName(TestConstants.USER_NAME);
        enterEmail(TestConstants.VALID_EMAIL);
        enterPassword(TestConstants.VALID_PASSWORD);
        clickRegisterButton();
    }

    /**
     * Выполняет регистрацию с коротким паролем (для теста ошибки)
     */
    public void registerWithShortPassword() {
        enterName(TestConstants.USER_NAME);
        enterEmail(TestConstants.VALID_EMAIL);
        enterPassword(TestConstants.SHORT_PASSWORD);
        clickRegisterButton();
    }

    /**
     * Универсальный метод регистрации (для кастомных данных)
     */
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    /**
     * Генерирует уникальный email для тестов регистрации
     * Чтобы избежать ошибки "Такой пользователь уже существует"
     */
    public String generateUniqueEmail() {
        return "burger_test_" + System.currentTimeMillis() + "@mail.ru";
    }
}
