package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object для страницы регистрации
 * Инкапсулирует элементы и действия на странице регистрации пользователя
 */
public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы элементов страницы регистрации
    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private final By loginLink = By.xpath("//a[contains(text(), 'Войти')]");
    private final By passwordError = By.cssSelector("p.input__error");

    /**
     * Конструктор инициализирует драйвер и ожидание
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Вводит имя пользователя
     *
     * @param name имя для ввода
     */
    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    /**
     * Вводит email пользователя
     *
     * @param email email для ввода
     */
    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    /**
     * Вводит пароль пользователя
     *
     * @param password пароль для ввода
     */
    @Step("Ввод пароля")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    /**
     * Нажимает кнопку регистрации
     */
    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    /**
     * Нажимает ссылку для перехода к авторизации
     *
     * @return Page Object страницы авторизации
     */
    @Step("Нажатие ссылки 'Войти'")
    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }

    /**
     * Получает текст ошибки валидации пароля
     *
     * @return текст ошибки
     */
    @Step("Получение текста ошибки пароля")
    public String getPasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    /**
     * Проверяет загрузку страницы регистрации
     *
     * @return true если кнопка регистрации отображается
     */
    @Step("Проверка загрузки страницы регистрации")
    public boolean isRegisterPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).isDisplayed();
    }

    /**
     * Выполняет полный процесс регистрации пользователя
     *
     * @param name     имя пользователя
     * @param email    email пользователя
     * @param password пароль пользователя
     */
    @Step("Регистрация пользователя: {name}, {email}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
}
