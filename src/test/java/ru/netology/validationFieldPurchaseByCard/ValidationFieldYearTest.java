package ru.netology.validationFieldPurchaseByCard;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationFieldYearTest {

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
    public void shouldEnterNumbersInFieldYear() {
        Page.fieldYear.setValue("24");
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals("24", actualContentsField);
    }

    @Test
    public void shouldNotEnterMore2NumbersInFieldYear() {
        Page.fieldYear.setValue("123");
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals("12", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersLatinInFieldYear() {
        Page.fieldYear.setValue("ya");
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldYear() {
        Page.fieldYear.setValue("го");
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterSymbolsInFieldYear() {
        Page.fieldYear.setValue("!@#$%^&*()_+/-,. `~");
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals("", actualContentsField);
    }
}