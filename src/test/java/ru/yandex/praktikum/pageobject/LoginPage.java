package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.constants.TestConstants.*;

/**
 * Page Object для страницы авторизации
 * Содержит методы для взаимодействия с элементами страницы входа в систему
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы элементов страницы
    private final By emailField = By.cssSelector("input[name='name']");
    private final By passwordField = By.cssSelector("input[name='Пароль']");
    private final By loginButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    private final By registerLink = By.cssSelector("a[href='/register']");
    private final By recoverPasswordLink = By.cssSelector("a[href='/forgot-password']");
    private final By errorMessage = By.cssSelector("p.input__error.text_type_main-default");

    /**
     * Конструктор инициализирует драйвер и ожидание
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Вводит email в поле ввода
     *
     * @param email email для ввода
     */
    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    /**
     * Вводит пароль в поле ввода
     *
     * @param password пароль для ввода
     */
    @Step("Ввод пароля")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    /**
     * Нажимает кнопку входа
     */
    @Step("Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Нажимает ссылку для перехода к регистрации
     *
     * @return Page Object страницы регистрации
     */
    @Step("Нажатие ссылки 'Зарегистрироваться'")
    public RegisterPage clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new RegisterPage(driver);
    }

    /**
     * Нажимает ссылку для восстановления пароля
     */
    @Step("Нажатие ссылки 'Восстановить пароль'")
    public void clickRecoverPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordLink)).click();
    }

    /**
     * Получает текст сообщения об ошибке
     *
     * @return текст ошибки
     */
    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    /**
     * Проверяет загрузку страницы логина
     *
     * @return true если страница загружена
     */
    @Step("Проверка загрузки страницы логина")
    public boolean isLoginPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }

    /**
     * Создает пользователя через API и возвращает email
     *
     * @return email созданного пользователя
     */
    @Step("Создание тестового пользователя через API")
    public String createUserViaApi() {
        String email = generateUniqueEmail();
        String requestBody = String.format(
                "{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}",
                email, VALID_PASSWORD, USER_NAME
        );

        io.restassured.response.Response response = io.restassured.RestAssured.given()
                .header("Content-type", "application/json")
                .baseUri("https://stellarburgers.education-services.ru/api/")
                .body(requestBody)
                .when()
                .post("auth/register");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Не удалось создать пользователя через API: " + response.getBody().asString());
        }

        return email;
    }
}