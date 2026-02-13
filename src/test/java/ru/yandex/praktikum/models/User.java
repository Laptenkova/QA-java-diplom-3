package ru.yandex.praktikum.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель данных пользователя для регистрации в системе Stellar Burgers.
 * Содержит обязательные поля: email, password и name.
 * Используется для сериализации в JSON при отправке запросов к API.
 */
@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;
}
