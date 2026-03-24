package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class GiftDetailsPage extends AbsBasePage {

    private final SelenideElement reserveButton = $(id("ru.otus.wishlist:id/reserve_button"));
    private final SelenideElement reservedStatus = $(id("ru.otus.wishlist:id/reserved_status"));

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