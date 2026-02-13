package ru.yandex.praktikum.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Утилитный класс для настройки REST API запросов
 * Содержит базовые настройки для всех API запросов в проекте
 */
public class ApiConfig {

    /**
     * Базовый URL для API запросов
     */
    public static final String API_BASE_URL = "https://stellarburgers.education-services.ru/api/";

    /**
     * Создает базовую спецификацию для всех API запросов
     * Включает базовый URL и Content-Type
     *
     * @return RequestSpecification с базовыми настройками
     */
    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(API_BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}