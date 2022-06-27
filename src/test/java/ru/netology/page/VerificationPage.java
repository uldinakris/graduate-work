package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {

    private SelenideElement purchaseByCardButton = $x("//button[@type='button']/span[.='Купить']");
    private SelenideElement purchaseOnCreditButton = $x("//button[@type='button']/span[.='Купить в кредит']");
    private SelenideElement cardNumber = $x("//span[text()='Номер карты']/..//input");
    private SelenideElement month = $x("//span[text()='Месяц']/..//input");
    private SelenideElement year = $x("//span[text()='Год']/..//input");
    private SelenideElement owner = $x("//span[text()='Владелец']/..//input");
    private SelenideElement cvc = $x("//span[text()='CVC/CVV']/..//input");
    private SelenideElement proceedButton = $x("//button[@type='button']/span[contains(.,'Продолжить')]");
    private SelenideElement successNotification = $x("//*[contains(text(),'Успешно')]");
    private SelenideElement declinedNotification = $x("//*[contains(text(),'Операция отклонена')]");
    private SelenideElement warningNotification = $x("//*[contains(text(),'Неверный формат')]");

    public void purchaseByCard(DataHelper.CardInfo cardInfo) {
        purchaseByCardButton.click();
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        cvc.setValue(cardInfo.getCvc());
        proceedButton.click();
    }

    public void purchaseOnCredit(DataHelper.CardInfo cardInfo) {
        purchaseOnCreditButton.click();
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        cvc.setValue(cardInfo.getCvc());
        proceedButton.click();
    }

    public SelenideElement getSuccessNotification() {
        return successNotification;
    }

    public SelenideElement getDeclinedNotification() {
        return declinedNotification;
    }

    public SelenideElement getWarningNotification() {
        return warningNotification;
    }
}
