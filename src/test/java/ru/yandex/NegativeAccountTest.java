package ru.yandex;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Негативные тесты")
@RunWith(MockitoJUnitRunner.class)
public class NegativeAccountTest {

    @Test
    @DisplayName("Проверка имени по длине - меньше минимальной")
    public void checkNameLengthTooShortIsFailed() {
        String name = "Ти";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " должно быть не валидным, длина меньше допустимой",
                new Account(name).checkNameLength(),
                equalTo(false)
        );
    }

    @Test
    @DisplayName("Проверка имени по длине - больше максимальной")
    public void checkNameLengthTooLongIsFailed() {
        String name = "Тимоти-Тимоти Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " должно быть не валидным, длина больше допустимой",
                new Account(name).checkNameLength(),
                equalTo(false)
        );
    }

    @Test
    @DisplayName("Проверка имени с несколькими пробелами")
    public void checkNameManySpacesIsFailed() {
        String name = "Тимоти Тим Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " не валидно - содержит больше одного пробела",
                new Account(name).checkNameOneSpace(),
                equalTo(false)
        );
    }

    @Test
    @DisplayName("Проверка имени без пробелов")
    public void checkNameNoSpaceIsFailed() {
        String name = "ТимотиШаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + "не валидно - не содержит пробел",
                new Account(name).checkNameOneSpace(),
                equalTo(false)
        );
    }

    @Test
    @DisplayName("Проверка имени - начинается с пробела")
    public void checkNameSpaceStartsIsFailed () {
        String name = " Тимоти Шаламе";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " не валидно - начинается с пробела",
                new Account(name).checkNameSpaceStartOrEnd(),
                equalTo(false)
        );
    }

    @Test
    @DisplayName("Проверка имени - заканчивается пробелом")
    public void checkNameEndsIsFailed () {
        String name = "Тимоти Шаламе ";
        Allure.addAttachment("Имя", name);

        MatcherAssert.assertThat(
                "Имя " + name + " не валидно - заканчивается пробелом",
                new Account(name).checkNameSpaceStartOrEnd(),
                equalTo(false)
        );
    }

    @Test
    @DisplayName("Проверка по всем требованиям: некорректная длина")
    public void checkNameToEmbossErrorLengthIsFailed() {
        Account spyAccount = Mockito.spy(new Account("T"));

        MatcherAssert.assertThat(
                "Не сработало требование по длине",
                spyAccount.checkNameToEmboss(),
                equalTo(false)
        );
    }

    @Test
    @DisplayName("Проверка по всем требованиям: больше одного пробела")
    public void checkNameToEmbossErrorOneSpaceIsFailed() {
        Account spyAccount = Mockito.spy(new Account("Tим Том Ш"));

        MatcherAssert.assertThat(
                "Не сработало требование по количеству пробелов",
                spyAccount.checkNameToEmboss(),
                equalTo(false)
        );
    }
    @Test
    @DisplayName("Проверка по всем требованиям: пробел в начале строки")
    public void checkNameToEmbossErrorSpaceStartOrEndIsFailed() {
        Account spyAccount = Mockito.spy(new Account(" T"));

        MatcherAssert.assertThat(
                "Не сработало требование по пробелу в начале или в конце",
                spyAccount.checkNameToEmboss(),
                equalTo(false)
        );
    }
}
