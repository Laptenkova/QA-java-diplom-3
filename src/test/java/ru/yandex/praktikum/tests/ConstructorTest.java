package ru.yandex.praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.pageobject.MainPage;

import static org.junit.Assert.assertEquals;

/**
 * Тесты навигации по разделам конструктора бургеров
 * Наследует от BaseTest для автоматической инициализации WebDriver
 */
public class ConstructorTest extends BaseTest {

    /**
     * Тест перехода к разделу "Булки" в конструкторе бургеров
     * Проверяет корректность активации и отображения раздела с булками
     */
    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void navigateToBunsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBunsSection();
        assertEquals("Должен быть активен раздел Булки", "Булки", mainPage.getCurrentSectionText());
    }

    /**
     * Тест перехода к разделу "Соусы" в конструкторе бургеров
     * Проверяет корректность активации и отображения раздела с соусами
     */
    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void navigateToSaucesSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesSection();
        assertEquals("Должен быть активен раздел Соусы", "Соусы", mainPage.getCurrentSectionText());
    }

    /**
     * Тест перехода к разделу "Начинки" в конструкторе бургеров
     * Проверяет корректность активации и отображения раздела с начинками
     */
    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void navigateToFillingsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsSection();
        assertEquals("Должен быть активен раздел Начинки", "Начинки", mainPage.getCurrentSectionText());
    }
}