package main;

import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.EditWishlistPage;
import pages.LoginPage;
import pages.MyWishlistsPage;

@SuppressWarnings("unused")
@ExtendWith(AndroidExtension.class)
public class WishlistEditTest {

    @Inject
    private LoginPage loginPage;
    @Inject
    private MyWishlistsPage myWishlistsPage;
    @Inject
    private EditWishlistPage editWishlistPage;

    @Test
    void editWishlist() {
        loginPage.login("tonyp98", "12345678");
        String wishlistTitle = "Новый год";
        String newWishlistDescription = "К нам уже не мчится";
        myWishlistsPage
                .assertNumberOfWishlists(1)
                .assertWhishlistTitle(1, wishlistTitle)
                .assertWhishlistSubTitle(1, "К нам мчится, скоро все случится")
                .pushEditWhishlist(1);
        editWishlistPage
                .assertEditWishlistTitle("Изменить список желаний")
                .editDescription(newWishlistDescription);
        myWishlistsPage
                .assertNumberOfWishlists(1)
                .assertWhishlistTitle(1, wishlistTitle)
                .assertWhishlistSubTitle(1, newWishlistDescription);
    }
}
