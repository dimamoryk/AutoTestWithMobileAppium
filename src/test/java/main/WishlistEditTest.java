package main;

import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.EditWishlistPage;
import pages.LoginPage;
import pages.MyWishlistsPage;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class WishlistEditTest {

    @Inject
    private LoginPage loginPage;
    @Inject
    private MyWishlistsPage myWishlistsPage;
    @Inject
    private EditWishlistPage editWishlistPage;
    @Inject
    private DatabaseUtils databaseUtils;

    private static final String TEST_USER = "wishlist_test";
    private static final String TEST_PASSWORD = "12345678";

    @Test
    void editWishlist() {
        // ARRANGE — подготовка данных
        databaseUtils.updateWishlistDescription(TEST_USER, "К нам мчится, скоро все случится");

        // ACT
        loginPage.login(TEST_USER, TEST_PASSWORD);

        myWishlistsPage
                .assertNumberOfWishlists(1)
                .assertWishlistTitle(1, "Новый год")
                .assertWishlistSubTitle(1, "К нам мчится, скоро все случится")
                .pushEditWishlist(1);

        editWishlistPage
                .assertTitle("Изменить список желаний")
                .editDescription("К нам уже не мчится");

        // ASSERT
        myWishlistsPage
                .assertNumberOfWishlists(1)
                .assertWishlistTitle(1, "Новый год")
                .assertWishlistSubTitle(1, "К нам уже не мчится");
    }
}
