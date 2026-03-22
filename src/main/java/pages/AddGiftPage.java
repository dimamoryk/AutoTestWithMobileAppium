package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

@Singleton
public class AddGiftPage extends AbsBasePage {

    private final SelenideElement titleInput = $("ru.otus.wishlist:id/gift_title_input").as("Поле названия");
    private final SelenideElement descriptionInput = $("ru.otus.wishlist:id/gift_description_input").as("Поле описания");
    private final SelenideElement urlInput = $("ru.otus.wishlist:id/gift_url_input").as("Поле ссылки");
    private final SelenideElement saveButton = $("ru.otus.wishlist:id/save_button").as("Кнопка сохранения");

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

