package ru.netology.validationFietd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationFieldCardHolderTest {

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