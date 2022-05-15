package ru.netology.validationFieldPurchaseByCard;

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

public class ValidationFieldCvcCvvTest {

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
    public void shouldEnterDigitsInFieldCvC() {
        String digits = DataHelper.getThreeDigits();
        Page.fieldCvC.setValue(digits);
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals(digits, actualContentsField);
    }

    @Test
    public void shouldNotEnterMore3DigitsInFieldCvC() {
        String digits = DataHelper.getFourDigits();
        Page.fieldCvC.setValue(digits);
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals(digits.substring(0, 3), actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersLatinInFieldCvC() {
        Page.fieldCvC.setValue(DataHelper.getLatinFirstName().substring(0, 2));
        String actualContentsField = Page.fieldCvC.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldCvC() {
        Page.fieldCvC.setValue(DataHelper.getCyrillicFirstName().substring(0, 2));
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