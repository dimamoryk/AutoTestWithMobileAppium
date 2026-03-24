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

    private static final String OWNER_USER = "owner";
    private static final String FRIEND_USER = "reservation_test";
    private static final String TEST_PASSWORD = "12345678";

    @Test
    void shouldReserveGift() {
        databaseUtils.resetGiftReservation(OWNER_USER, "Подарки", "Книга");

        // Резервирование подарка другом
        loginPage.login(FRIEND_USER, TEST_PASSWORD);
        myWishlistsPage.openUserWishlist(OWNER_USER);
        wishlistDetailsPage.openGift("Книга");
        giftDetailsPage.reserve();
        giftDetailsPage.assertReserved();

        // Проверка от имени владельца
        loginPage.login(OWNER_USER, TEST_PASSWORD);
        myWishlistsPage.openWishlistByName("Подарки");
        wishlistDetailsPage.openGift("Книга");
        giftDetailsPage.assertReservedBy(FRIEND_USER);
    }
}