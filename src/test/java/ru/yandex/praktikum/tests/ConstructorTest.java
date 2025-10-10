package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.factory.DriverFactory;
import ru.yandex.praktikum.pageobject.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private DriverFactory driverFactory;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driverFactory = new DriverFactory();
        mainPage = new MainPage(driverFactory.getDriver());
    }

    @After
    public void tearDown() {
        if (driverFactory != null) {
            driverFactory.quitDriver();
        }
    }

    @Test
    public void navigateToBunsSectionTest() {
        mainPage.clickBunsSection();
        assertEquals("Должен быть активен раздел Булки", "Булки", mainPage.getCurrentSectionText());
    }

    @Test
    public void navigateToSaucesSectionTest() {
        mainPage.clickSaucesSection();
        assertEquals("Должен быть активен раздел Соусы", "Соусы", mainPage.getCurrentSectionText());
    }

    @Test
    public void navigateToFillingsSectionTest() {
        mainPage.clickFillingsSection();
        assertEquals("Должен быть активен раздел Начинки", "Начинки", mainPage.getCurrentSectionText());
    }
}
