package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static io.appium.java_client.AppiumBy.id;

public class GiftsContent extends AbsComponent<GiftsContent> {

    private final ElementsCollection items;

    public GiftsContent(SelenideElement root) {
        super(root);
        this.items = root.$$(id("ru.otus.wishlist:id/gift_item"));
    }

    public GiftItem get(int index) {
        return new GiftItem(items.get(index - 1));
    }

    public void assertSizeEqualsTo(int expected) {
        items.shouldHave(size(expected));
    }
}
