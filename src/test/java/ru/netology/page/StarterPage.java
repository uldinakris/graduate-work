package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class StarterPage {

    private SelenideElement purchaseByCardButton = $x("//button[@type='button']/span[.='Купить']");
    private SelenideElement purchaseOnCreditButton = $x("//button[@type='button']/span[.='Купить в кредит']");

    public StarterPage() {
        open("http://localhost:8080");
        Configuration.holdBrowserOpen = true;
        purchaseByCardButton.shouldBe(Condition.visible);
    }

    public DebitPurchasePage openPurchasePage() {
        purchaseByCardButton.click();
        return new DebitPurchasePage();
    }

    public CreditPurchasePage openCreditPurchasePage() {
        purchaseOnCreditButton.click();
        return new CreditPurchasePage();
    }
}
