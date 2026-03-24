package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class EditWishlistPage extends AbsBasePage {

    private final SelenideElement title = $(id("ru.otus.wishlist:id/wishlist_edit_title"));
    private final SelenideElement descriptionInput = $(id("ru.otus.wishlist:id/description_input"));
    private final SelenideElement saveButton = $(id("ru.otus.wishlist:id/save_button"));

    public EditWishlistPage assertTitle(String expected) {
        title.shouldBe(visible).shouldHave(text(expected));
        return this;
    }

    public void editDescription(String description) {
        descriptionInput.shouldBe(visible).clear();
        descriptionInput.sendKeys(description);
        saveButton.shouldBe(visible).click();
    }
}