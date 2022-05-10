package ru.netology.validationFietd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationFieldCvcCvvTest {

    @BeforeEach
    public void openBrowser() {
        open("http://localhost:8080/");
        Page.buttonBuy.click();
    }

    @Test
    public void shouldEnterNumbersInFieldCvC() {
        Page.fieldCvC.setValue("123");
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals("123", actualContentsField);
    }

    @Test
    public void shouldNotEnterMore3NumbersInFieldCvC() {
        Page.fieldCvC.setValue("1234");
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals("123", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersLatinInFieldCvC() {
        Page.fieldCvC.setValue("cv");
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldCvC() {
        Page.fieldCvC.setValue("ко");
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterSymbolsInFieldCvC() {
        Page.fieldCvC.setValue("!@#$%^&*()_+/-,. `~");
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals("", actualContentsField);
    }
}