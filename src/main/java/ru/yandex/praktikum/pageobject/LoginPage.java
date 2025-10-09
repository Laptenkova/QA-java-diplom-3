package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.constants.TestConstants;

import java.time.Duration;

/**
 * Page Object для страницы авторизации Stellar Burgers
 * Содержит элементы формы входа и методы для работы с ними
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы формы входа
    private final By emailField = By.cssSelector("input[name='name']");
    private final By passwordField = By.cssSelector("input[name='Пароль']");
    private final By loginButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");

    // Локаторы дополнительных ссылок
    private final By registerLink = By.cssSelector("a[href='/register']");
    private final By recoverPasswordLink = By.cssSelector("a[href='/forgot-password']");

    // Локатор для ошибок
    private final By errorMessage = By.cssSelector("p.input__error.text_type_main-default");

    /**
     * Конструктор страницы авторизации
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
     * Кликает на кнопку "Войти"
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Кликает на ссылку "Зарегистрироваться"
     */
    public RegisterPage clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new RegisterPage(driver);
    }

    /**
     * Кликает на ссылку "Восстановить пароль"
     */
    public void clickRecoverPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordLink)).click();
    }

    /**
     * Получает текст сообщения об ошибке
     */
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    /**
     * Проверяет, что страница авторизации загружена
     */
    public boolean isLoginPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }

    /**
     * Выполняет полный вход в систему с валидными данными
     */
    public void loginWithValidCredentials() {
        enterEmail(TestConstants.VALID_EMAIL);
        enterPassword(TestConstants.VALID_PASSWORD);
        clickLoginButton();
    }

    /**
     * Выполняет вход с неверным паролем (для теста ошибки)
     */
    public void loginWithInvalidPassword() {
        enterEmail(TestConstants.VALID_EMAIL);
        enterPassword("wrongpassword"); // Специально неверный пароль
        clickLoginButton();
    }

    /**
     * Выполняет вход с неверным email (для теста ошибки)
     */
    public void loginWithInvalidEmail() {
        enterEmail("nonexistent@test.com"); // Специально неверный email
        enterPassword(TestConstants.VALID_PASSWORD);
        clickLoginButton();
    }

    /**
     * Универсальный метод входа (для кастомных данных)
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}