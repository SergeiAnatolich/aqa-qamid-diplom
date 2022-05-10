package ru.netology.validationFietd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationFieldNumberCardTest {

    @BeforeEach
    public void openBrowser() {
        open("http://localhost:8080/");
        Page.buttonBuy.click();
    }

    @Test
    public void shouldEnterNumbersInFieldCard() {
        Page.fieldCardNumber.setValue("1234");
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals("1234", actualContentsField);
    }

    @Test
    public void shouldNotEnterMore16NumbersInFieldCard() {
        Page.fieldCardNumber.setValue("1111 2222 3333 4444 5555");
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals("1111 2222 3333 4444", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersLatinInFieldCard() {
        Page.fieldCardNumber.setValue("number");
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldCard() {
        Page.fieldCardNumber.setValue("номерё");
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterSymbolsInFieldCard() {
        Page.fieldCardNumber.setValue("!@#$%^&*()_+/-,. `~");
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals("", actualContentsField);
    }
}