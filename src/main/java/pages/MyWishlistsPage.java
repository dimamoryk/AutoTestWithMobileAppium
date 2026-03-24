package pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.WishlistContent;
import components.WishlistItem;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.AppiumSelectors.byText;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class MyWishlistsPage extends AbsBasePage {

    private final WishlistContent wishlistContent = new WishlistContent($(id("ru.otus.wishlist:id/wishlists")));

    public MyWishlistsPage assertNumberOfWishlists(int value) {
        wishlistContent.shouldBe(visible).assertSizeEqualsTo(value);
        return this;
    }

    public MyWishlistsPage assertWishlistSubTitle(int index, String value) {
        getWishlistItem(index).assertSubTitleEqualsTo(value);
        return this;
    }

    public void pushEditWishlist(int index) {
        getWishlistItem(index).pushEdit();
    }

    public MyWishlistsPage assertWishlistTitle(int index, String value) {
        getWishlistItem(index).assertTitleEqualsTo(value);
        return this;
    }

    public void openWishlistByName(String name) {
        SelenideElement wishlist = $(byText(name));
        wishlist.shouldBe(visible).click();
    }

    public void openUserWishlist(String username) {
        SelenideElement searchInput = $(id("ru.otus.wishlist:id/search_input"));
        if (searchInput.exists()) {
            searchInput.shouldBe(visible).sendKeys(username);
        }
        $(byText(username)).shouldBe(visible).click();
    }

    private WishlistItem getWishlistItem(int index) {
        return wishlistContent.get(index)
                .shouldBe(visible.because("Список желаний номер %d не виден на экране".formatted(index)));
    }
}