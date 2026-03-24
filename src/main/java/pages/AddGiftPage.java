package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class AddGiftPage extends AbsBasePage {

    private final SelenideElement titleInput = $(id("ru.otus.wishlist:id/gift_title_input"));
    private final SelenideElement descriptionInput = $(id("ru.otus.wishlist:id/gift_description_input"));
    private final SelenideElement urlInput = $(id("ru.otus.wishlist:id/gift_url_input"));
    private final SelenideElement saveButton = $(id("ru.otus.wishlist:id/save_button"));

    public AddGiftPage setTitle(String title) {
        titleInput.shouldBe(visible).sendKeys(title);
        return this;
    }

    public AddGiftPage setDescription(String description) {
        descriptionInput.shouldBe(visible).sendKeys(description);
        return this;
    }

    public AddGiftPage setUrl(String url) {
        urlInput.shouldBe(visible).sendKeys(url);
        return this;
    }

    public void save() {
        saveButton.shouldBe(visible).click();
    }
}