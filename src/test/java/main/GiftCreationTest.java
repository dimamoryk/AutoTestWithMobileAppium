package main;

import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AddGiftPage;
import pages.LoginPage;
import pages.MyWishlistsPage;
import pages.WishlistDetailsPage;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class GiftCreationTest {

    private static final String TEST_USER = "gift_test";
    private static final String TEST_PASSWORD = "12345678";
    @Inject
    private LoginPage loginPage;
    @Inject
    private MyWishlistsPage myWishlistsPage;
    @Inject
    private WishlistDetailsPage wishlistDetailsPage;
    @Inject
    private AddGiftPage addGiftPage;
    @Inject
    private DatabaseUtils databaseUtils;

    @Test
    void shouldCreateAndEditGift() {
        databaseUtils.clearGiftsForWishlist(TEST_USER, "Новый год");

        loginPage.login(TEST_USER, TEST_PASSWORD);
        myWishlistsPage.openWishlistByName("Новый год");
        wishlistDetailsPage.clickAddGift();

        addGiftPage.setTitle("PS5")
                .setDescription("PlayStation 5")
                .setUrl("https://wishlist.ru/ps5")
                .save();

        wishlistDetailsPage.assertGiftExists("PS5");

        wishlistDetailsPage.editGift("PS5");
        addGiftPage.setDescription("PlayStation 5 Digital Edition")
                .save();

        wishlistDetailsPage.assertGiftDescription("PS5", "PlayStation 5 Digital Edition");
    }
}
