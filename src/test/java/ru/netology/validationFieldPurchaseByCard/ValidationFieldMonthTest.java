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

public class ValidationFieldMonthTest {

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
    public void shouldEnterNumbersInFieldMonth() {
        String twoDigits = DataHelper.getTwoDigits();
        Page.fieldMonth.setValue(twoDigits);
        String actualContentsField = Page.fieldMonth.getValue();
        assertEquals(twoDigits, actualContentsField);
    }

    @Test
    public void shouldNotEnterMore2NumbersInFieldMonth() {
        String threeDigits = DataHelper.getThreeDigits();
        Page.fieldMonth.setValue(threeDigits);
        String actualContentsField = Page.fieldMonth.getValue();
        assertEquals(threeDigits.substring(0, 2), actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersLatinInFieldMonth() {
        Page.fieldMonth.setValue(DataHelper.getLatinFirstName().substring(0, 2));
        String actualContentsField = Page.fieldMonth.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldMonth() {
        Page.fieldMonth.setValue(DataHelper.getCyrillicFirstName().substring(0, 2));
        String actualContentsField = Page.fieldMonth.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterSymbolsInFieldMonth() {
        Page.fieldMonth.setValue("!@#$%^&*()_+/-,. `~");
        String actualContentsField = Page.fieldMonth.getValue();
        assertEquals("", actualContentsField);
    }
}