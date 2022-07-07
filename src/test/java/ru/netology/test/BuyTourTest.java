package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.netology.data.DataHelper;
import org.junit.jupiter.api.Test;
import ru.netology.page.StarterPage;

public class BuyTourTest {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldPurchaseByCard() {
        var starterPage = new StarterPage();
        var purchasePage = starterPage.openPurchasePage();
        var cardInfo = DataHelper.getApprovedCardInfo();
        purchasePage.purchaseByCard(cardInfo);
        purchasePage.checkSuccessNotification();
    }

    @Test
    void shouldNotPurchaseByCard() {
        var starterPage = new StarterPage();
        var purchasePage = starterPage.openPurchasePage();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        purchasePage.purchaseByCard(cardInfo);
        purchasePage.checkDeclinedNotification();
    }

    @Test
    void shouldPurchaseOnCredit() {
        var starterPage = new StarterPage();
        var purchaseOnCredit = starterPage.openCreditPurchasePage();
        var cardInfo = DataHelper.getApprovedCardInfo();
        purchaseOnCredit.purchaseOnCredit(cardInfo);
        purchaseOnCredit.checkSuccessNotification();
    }

    @Test
    void shouldNotPurchaseOnCredit() {
        var starterPage = new StarterPage();
        var purchaseOnCredit = starterPage.openCreditPurchasePage();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        purchaseOnCredit.purchaseOnCredit(cardInfo);
        purchaseOnCredit.checkDeclinedNotification();
    }

    @Test
    void shouldNotSendForm() {
        var starterPage = new StarterPage();
        var purchaseOnCredit = starterPage.openCreditPurchasePage();
        var cardInfo = DataHelper.getIncorrectCardInfo();
        purchaseOnCredit.purchaseOnCredit(cardInfo);
        purchaseOnCredit.checkWarningNotification();
    }
}
