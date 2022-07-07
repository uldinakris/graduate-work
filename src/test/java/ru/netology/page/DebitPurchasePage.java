package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DebitPurchasePage {

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
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        cvc.setValue(cardInfo.getCvc());
        proceedButton.click();
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkDeclinedNotification() {
        declinedNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkWarningNotification() {
        warningNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
