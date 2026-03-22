package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Singleton
public class GiftDetailsPage extends AbsBasePage {

    private final SelenideElement reserveButton = $("ru.otus.wishlist:id/reserve_button").as("Кнопка резервирования");
    private final SelenideElement reservedStatus = $("ru.otus.wishlist:id/reserved_status").as("Статус резервирования");

    public void reserve() {
        reserveButton.shouldBe(visible).click();
    }

    public void assertReserved() {
        reservedStatus.shouldHave(text("Зарезервировано"));
    }

    public void assertReservedBy(String username) {
        reservedStatus.shouldHave(text("Зарезервировано пользователем " + username));
    }
}
