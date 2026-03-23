package main;

import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class GiftCreationTest {

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

    private final String testLogin = "tonyp98";
    private final String testPassword = "12345678";

    @BeforeEach
    void prepareData() {
        databaseUtils.cleanGiftsForWishlist(testLogin, "Новый год");
    }

    @Test
    void shouldCreateAndEditGift() {
        loginPage.login(testLogin, testPassword);
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
