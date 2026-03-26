package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.GiftItem;
import components.GiftsContent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class WishlistDetailsPage extends AbsBasePage {

    private final SelenideElement addGiftButton = $(id("ru.otus.wishlist:id/add_gift_button"));
    private final GiftsContent giftsContent = new GiftsContent($(id("ru.otus.wishlist:id/gifts")));

    public void clickAddGift() {
        addGiftButton.shouldBe(visible).click();
    }

    public void assertGiftExists(String title) {
        getGiftItemByTitle(title).assertTitleEqualsTo(title);
    }

    public void editGift(String title) {
        getGiftItemByTitle(title).clickEdit();
    }

    public void assertGiftDescription(String title, String expectedDescription) {
        getGiftItemByTitle(title).assertDescriptionEqualsTo(expectedDescription);
    }

    public void openGift(String title) {
        // Находим элемент подарка и кликаем по нему
        SelenideElement giftElement = getGiftItemByTitle(title).getRoot();
        giftElement.shouldBe(visible).click();
    }

    private GiftItem getGiftItemByTitle(String title) {
        // Ищем подарок по названию
        for (int i = 1; i <= 10; i++) {
            try {
                GiftItem item = giftsContent.get(i);
                item.assertTitleEqualsTo(title);
                return item;
            } catch (AssertionError e) {
                // Продолжаем поиск, ошибку не логируем
            }
        }
        throw new AssertionError("Подарок с названием '" + title + "' не найден");
    }
}
