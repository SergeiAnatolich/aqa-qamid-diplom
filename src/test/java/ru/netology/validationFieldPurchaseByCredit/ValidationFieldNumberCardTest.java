package ru.netology.validationFieldPurchaseByCredit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationFieldNumberCardTest {

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
        Page.buttonBuyInCredit.click();
    }

    @Test
    public void shouldEnterDigitsInFieldCard() {
        String digits = DataHelper.getFourDigits();
        Page.fieldCardNumber.setValue(digits);
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals(digits, actualContentsField);
    }

    @Test
    public void shouldNotEnterMore16DigitsInFieldCard() {
        String digits = DataHelper.get20Digits();
        Page.fieldCardNumber.setValue(digits);
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals(digits.substring(0, 19), actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersLatinInFieldCard() {
        Page.fieldCardNumber.setValue(DataHelper.getLatinFirstName());
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldCard() {
        Page.fieldCardNumber.setValue(DataHelper.getCyrillicFirstName());
        String actualContentsField = Page.fieldCardNumber.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLetterCyrillicInFieldCard() {
        Page.fieldCardNumber.setValue("ё");
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