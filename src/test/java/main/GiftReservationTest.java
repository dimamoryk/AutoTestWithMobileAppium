package main;

import com.google.inject.Inject;
import extensions.AndroidExtension;
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

    private static final String OWNER = "owner";
    private static final String FRIEND = "reservation_test";
    private static final String PASSWORD = "12345678";

    @Test
    void shouldReserveGift() {
        databaseUtils.clearReservation(OWNER, "Подарки", "Книга");

        loginPage.login(FRIEND, PASSWORD);
        myWishlistsPage.openUserWishlist(OWNER);
        wishlistDetailsPage.openGift("Книга");
        giftDetailsPage.reserve();
        giftDetailsPage.assertReserved();

        loginPage.login(OWNER, PASSWORD);
        myWishlistsPage.openWishlistByName("Подарки");
        wishlistDetailsPage.openGift("Книга");
        giftDetailsPage.assertReservedBy(FRIEND);
    }
}
