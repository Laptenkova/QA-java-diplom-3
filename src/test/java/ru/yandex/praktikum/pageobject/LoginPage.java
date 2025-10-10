package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    private final By emailField = By.cssSelector("input[name='name']");
    private final By passwordField = By.cssSelector("input[name='Пароль']");
    private final By loginButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    private final By registerLink = By.cssSelector("a[href='/register']");
    private final By recoverPasswordLink = By.cssSelector("a[href='/forgot-password']");
    private final By errorMessage = By.cssSelector("p.input__error.text_type_main-default");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public RegisterPage clickRegisterLink() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }

    public void clickRecoverPasswordLink() {
        driver.findElement(recoverPasswordLink).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isLoginPageLoaded() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public By getLoginButtonLocator() {
        return loginButton;
    }
}
