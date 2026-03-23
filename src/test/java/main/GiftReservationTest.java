package main;

import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class GiftReservationTest {

    @Inject
    private LoginPage loginPage;
    @Inject
    private MyWishlistsPage myWishlistsPage;
    @Inject
    private WishlistDetailsPage wishlistDetailsPage;
    @Inject
    private GiftDetailsPage giftDetailsPage;
    @Inject
    private DatabaseUtils databaseUtils;

    private final String ownerLogin = "owner";
    private final String friendLogin = "tonyp98";
    private final String testPassword = "12345678";

    @BeforeEach
    void prepareData() {
        databaseUtils.createWishlist(ownerLogin, "Подарки");
        databaseUtils.createGift(ownerLogin, "Подарки", "Книга", "Интересная книга", "");
        databaseUtils.clearReservation(ownerLogin, "Подарки", "Книга");
    }

    @Test
    void shouldReserveGiftOfAnotherUser() {
        loginPage.login(friendLogin, testPassword);
        myWishlistsPage.openUserWishlist(ownerLogin);
        wishlistDetailsPage.openGift("Книга");
        giftDetailsPage.reserve();
        giftDetailsPage.assertReserved();

        loginPage.login(ownerLogin, testPassword);
        myWishlistsPage.openWishlistByName("Подарки");
        wishlistDetailsPage.openGift("Книга");
        giftDetailsPage.assertReservedBy(friendLogin);
    }
}
