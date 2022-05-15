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
        Page.buttonBuyInCredit.click();
    }

    @Test
    public void shouldEnterLettersLatinInFieldCardHolder() {
        String latinName = DataHelper.getLatinFirstName();
        Page.fieldCardHolder.setValue(latinName);
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals(latinName, actualContentsField);
    }

    @Test
    public void shouldEnterLettersLatinInFieldCardHolderWithSpace() {
        String latinName = DataHelper.getLatinFirstName() + " " + DataHelper.getLatinLastName();
        Page.fieldCardHolder.setValue(latinName);
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals(latinName, actualContentsField);
    }

    @Test
    public void shouldEnterLettersLatinInFieldCardHolderWithHyphen() {
        String latinName = DataHelper.getLatinFirstName() + "-" + DataHelper.getLatinFirstName();
        Page.fieldCardHolder.setValue(latinName);
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals(latinName, actualContentsField);
    }

    @Test
    public void shouldNotEnterLettersCyrillicInFieldCardHolder() {
        Page.fieldCardHolder.setValue(DataHelper.getCyrillicFirstName());
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
        Page.fieldCardHolder.setValue(DataHelper.getThreeDigits());
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterSymbolsInFieldCardHolder() {
        Page.fieldCardHolder.setValue("!@#$%^&*()_+/-,. `~");
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals("", actualContentsField);
    }

    @Test
    public void shouldNotEnterMore27LettersLatinInFieldCardHolder() {
        String latinLetters = DataHelper.getLatin30Letters();
        Page.fieldCardHolder.setValue(latinLetters);
        String actualContentsField = Page.fieldCardHolder.getValue();
        assertEquals(latinLetters.substring(0, 27), actualContentsField);
    }
}