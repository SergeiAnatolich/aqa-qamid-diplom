package ru.netology.validationFieldTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationFieldCardHolderTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openBrowser() {
        open("http://localhost:8080/");
        Page.buttonBuy.click();
    }

    @Test
    public void shouldEnterLettersLatinInFieldCardHolder() {
        Page.fieldCardHolder.setValue("ivan");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("ivan", actualContentsField);
    }

    @Test
    public void shouldEnterLettersLatinInFieldCardHolderWithSpace() {
        Page.fieldCardHolder.setValue("ivan ivanov");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("ivan ivanov", actualContentsField);
    }

    @Test
    public void shouldEnterLettersLatinInFieldCardHolderWithHyphen() {
        Page.fieldCardHolder.setValue("anna-maria ivanova-sidorova");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("anna-maria ivanova-sidorova", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldCardHolder() {
        Page.fieldCardHolder.setValue("иван иванов");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLetterCyrillicInFieldCardHolder() {
        Page.fieldCardHolder.setValue("ё");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterNumbersInFieldCardHolder() {
        Page.fieldCardHolder.setValue("123");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterSymbolsInFieldCardHolder() {
        Page.fieldCardHolder.setValue("!@#$%^&*()_+/-,. `~");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("", actualContentsField);
    }
}