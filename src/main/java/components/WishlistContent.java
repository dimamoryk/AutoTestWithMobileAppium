package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static io.appium.java_client.AppiumBy.id;

public class WishlistContent extends AbsComponent<WishlistContent> {

    private final ElementsCollection items;

    public WishlistContent(SelenideElement root) {
        super(root);
        this.items = root.$$(id("ru.otus.wishlist:id/wishlist_item"));
    }

    public WishlistItem get(int index) {
        return new WishlistItem(items.get(index - 1));
    }

    public void assertSizeEqualsTo(int expected) {
        items.shouldHave(size(expected).because("Неожидаемое количество списков желаний"));
    }
}