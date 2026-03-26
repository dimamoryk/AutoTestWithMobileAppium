package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

public class GiftItem extends AbsComponent<GiftItem> {

    private final SelenideElement title;
    private final SelenideElement description;
    private final SelenideElement editButton;

    public GiftItem(SelenideElement root) {
        super(root);
        this.title = root.$(id("ru.otus.wishlist:id/gift_title"));
        this.description = root.$(id("ru.otus.wishlist:id/gift_description"));
        this.editButton = root.$(id("ru.otus.wishlist:id/edit_gift_button"));
    }

    public void assertTitleEqualsTo(String value) {
        title.shouldHave(text(value));
    }

    public void assertDescriptionEqualsTo(String value) {
        description.shouldHave(text(value));
    }

    public void clickEdit() {
        editButton.shouldBe(visible).click();
    }

    // Добавляем метод для получения корневого элемента
    public SelenideElement getRoot() {
        return root;
    }
}
