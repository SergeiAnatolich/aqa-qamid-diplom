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
        Page.buttonBuyInCredit.click();
    }

    @Test
    public void shouldEnterDigitsInFieldYear() {
        String twoDigits = DataHelper.getTwoDigits();
        Page.fieldYear.setValue(twoDigits);
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals(twoDigits, actualContentsField);
    }

    @Test
    public void shouldNotEnterMore2DigitsInFieldYear() {
        String threeDigits = DataHelper.getThreeDigits();
        Page.fieldYear.setValue(threeDigits);
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals(threeDigits.substring(0, 2), actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersLatinInFieldYear() {
        Page.fieldYear.setValue(DataHelper.getLatinFirstName().substring(0, 2));
        String actualContentsField = Page.fieldYear.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldYear() {
        Page.fieldYear.setValue(DataHelper.getCyrillicFirstName().substring(0, 2));
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