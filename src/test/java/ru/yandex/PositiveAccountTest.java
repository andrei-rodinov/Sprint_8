package ru.yandex;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@DisplayName("Позитивные тесты")
public class PositiveAccountTest {

    @Test
    @DisplayName("Проверка валидности имени по всем параметрам")
    public void checkNameToEmbossIsSuccess() {
        String name = "Тимоти Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " является валидным",
                new Account(name).checkNameToEmboss(),equalTo(true)
        );
    }

    @Test
    @DisplayName("Проверка имени по длине")
    public void checkNameLengthIsSuccess() {
        String name = "Тимоти Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Длина имени " + name + " в валидном диапазоне",
                new Account(name).checkNameLength(),equalTo(true)
        );
    }

    @Test
    @DisplayName("Проверка минимальной длины имени")
    public void checkNameMinLengthIsSuccess() {
        String name = "Тим";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " валидно и содержит минимальное количество символов",
                new Account(name).checkNameLength(),equalTo(true)
        );
    }

    @Test
    @DisplayName("Проверка максимальной длины имени")
    public void checkNameMaxLengthIsSuccess() {
        String name = "Тимоти-Тимот Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " валидно и содержит максимальное количество символов",
                new Account(name).checkNameLength(),equalTo(true)
        );
    }

    @Test
    @DisplayName("Имя содержит один пробел")
    public void checkNameOneSpaceIsSuccess() {
        String name = "Тимоти Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " содержит один пробел",
                new Account(name).checkNameOneSpace(),equalTo(true)
        );
    }

    @Test
    @DisplayName("Имя не содержит пробел в начале и в конце")
    public void checkNameSpaceStartOrEndIsSuccess() {
        String name = "Тимоти Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " не начинается и не заканчивается пробелом",
                new Account(name).checkNameSpaceStartOrEnd(),equalTo(true)
        );
    }
}