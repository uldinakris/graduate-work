package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String owner;
        private String month;
        private String year;
        private String cardNumber;
        private String cvc;

        public static CardInfo getCardInfo(String locale, String cardNumber) {
            LocalDate date = LocalDate.now().plusYears(1);
            String month = date.format(DateTimeFormatter.ofPattern("MM"));
            String year = date.format(DateTimeFormatter.ofPattern("yy"));

            Faker faker = new Faker(new Locale(locale));
            return new CardInfo(faker.name().username(), month, year, cardNumber, "123");
        }

    }
    public static CardInfo getApprovedCardInfo() {
        return CardInfo.getCardInfo("ru", "4444 4444 4444 4441");
    }
    public static CardInfo getDeclinedCardInfo() {
        return CardInfo.getCardInfo("ru", "4444 4444 4444 4442");
    }
    public static CardInfo getIncorrectCardInfo() {
        return CardInfo.getCardInfo("ru", "4442");
    }
}
