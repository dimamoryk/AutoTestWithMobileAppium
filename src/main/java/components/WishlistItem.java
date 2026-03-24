package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

public class WishlistItem extends AbsComponent<WishlistItem> {

    private final SelenideElement title;
    private final SelenideElement subtitle;
    private final SelenideElement editButton;

    public WishlistItem(SelenideElement root) {
        super(root);
        this.title = root.$(id("ru.otus.wishlist:id/title"));
        this.subtitle = root.$(id("ru.otus.wishlist:id/subtitle"));
        this.editButton = root.$(id("ru.otus.wishlist:id/edit_button"));
    }

    public void assertTitleEqualsTo(String value) {
        title.shouldHave(text(value).because("Заголовок списка желаний не совпадает с ожидаемым"));
    }

    public void assertSubTitleEqualsTo(String value) {
        subtitle.shouldHave(text(value).because("Подзаголовок списка желаний не совпадает с ожидаемым"));
    }

    public void pushEdit() {
        editButton.shouldBe(visible.because("Кнопка редактирования желаний не видна на экране")).click();
    }
}