package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.appium.SelenideAppium.$$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class WishlistDetailsPage extends AbsBasePage {

    private final SelenideElement addGiftButton = $(id("ru.otus.wishlist:id/add_gift_button"));
    private final ElementsCollection giftTitles = $$(id("ru.otus.wishlist:id/gift_title"));

    public void clickAddGift() {
        addGiftButton.shouldBe(visible).click();
    }

    public void assertGiftExists(String title) {
        giftTitles.shouldHave(text(title));
    }

    public void editGift(String title) {
        SelenideElement giftItem = findGiftItemByTitle(title);
        SelenideElement editButton = giftItem.$(id("ru.otus.wishlist:id/edit_gift_button"));
        editButton.shouldBe(visible).click();
    }

    public void assertGiftDescription(String title, String expectedDescription) {
        SelenideElement giftItem = findGiftItemByTitle(title);
        SelenideElement description = giftItem.$(id("ru.otus.wishlist:id/gift_description"));
        description.shouldHave(text(expectedDescription));
    }

    public void openGift(String title) {
        findGiftItemByTitle(title).click();
    }

    private SelenideElement findGiftItemByTitle(String title) {
        return giftTitles.filter(text(title)).first().parent();
    }
}