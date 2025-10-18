package ru.yandex.praktikum.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель данных пользователя для авторизации в системе Stellar Burgers.
 * Содержит обязательные поля: email, password.
 * Используется для сериализации в JSON при отправке запросов к API.
 */
@Data
@AllArgsConstructor
public class UserCredentials {
    private String email;
    private String password;
}
