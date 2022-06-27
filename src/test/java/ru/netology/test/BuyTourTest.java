package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.netology.data.DataHelper;
import org.junit.jupiter.api.Test;
import ru.netology.page.VerificationPage;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.open;

public class BuyTourTest {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide()); }

    @AfterAll
    static void tearDownAll() { SelenideLogger.removeListener("allure");}

    @Test
    void shouldPurchaseByCard() {
        open("http://localhost:8080");
        Configuration.holdBrowserOpen = true;
        var verificationPage = new VerificationPage();
        var cardInfo = DataHelper.CardInfo.getCardInfo("ru", "4444 4444 4444 4441");
        verificationPage.purchaseByCard(cardInfo);
        var successNotification = verificationPage.getSuccessNotification();

        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldNotPurchaseByCard() {
        open("http://localhost:8080");
        Configuration.holdBrowserOpen = true;
        var verificationPage = new VerificationPage();
        var cardInfo = DataHelper.CardInfo.getCardInfo("ru", "4444 4444 4444 4442");
        verificationPage.purchaseByCard(cardInfo);
        var declinedNotification = verificationPage.getDeclinedNotification();

        declinedNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldPurchaseOnCredit() {
        open("http://localhost:8080");
        Configuration.holdBrowserOpen = true;
        var verificationPage = new VerificationPage();
        var cardInfo = DataHelper.CardInfo.getCardInfo("ru", "4444 4444 4444 4441");
        verificationPage.purchaseOnCredit(cardInfo);
        var successNotification = verificationPage.getSuccessNotification();

        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldNotPurchaseOnCredit() {
        open("http://localhost:8080");
        Configuration.holdBrowserOpen = true;
        var verificationPage = new VerificationPage();
        var cardInfo = DataHelper.CardInfo.getCardInfo("ru", "4444 4444 4444 4441");
        verificationPage.purchaseOnCredit(cardInfo);
        var declinedNotification = verificationPage.getDeclinedNotification();

        declinedNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotSendForm() {
        open("http://localhost:8080");
        Configuration.holdBrowserOpen = true;
        var verificationPage = new VerificationPage();
        var cardInfo = DataHelper.CardInfo.getCardInfo("ru", "4441");
        verificationPage.purchaseByCard(cardInfo);
        var warningNotification = verificationPage.getWarningNotification();

        warningNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
