package ru.otus.utils;

import com.google.inject.Singleton;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

@Singleton
public class DatabaseUtils {

    private final String url;
    private final String username;
    private final String password;

    public DatabaseUtils() {
        this.url = Objects.requireNonNull(
                System.getProperty("database.url"),
                "Укажите URL БД в проперти database.url"
        );
        this.username = Objects.requireNonNull(
                System.getProperty("database.username"),
                "Укажите логин БД в проперти database.username"
        );
        this.password = Objects.requireNonNull(
                System.getProperty("database.password"),
                "Укажите пароль БД в проперти database.password"
        );
    }

    @SneakyThrows
    public void updateWishlistDescription(String username, String description) {
        String sql = "UPDATE wishlists SET description = ? WHERE user_id = (SELECT id FROM users WHERE username = ?)";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, description);
            ps.setString(2, username);
            ps.executeUpdate();
        }
    }

    @SneakyThrows
    public void clearGifts(String username, String wishlistName) {
        String sql = "DELETE FROM gifts WHERE wishlist_id IN (SELECT id FROM wishlists WHERE user_id = (SELECT id FROM users WHERE username = ?) AND name = ?)";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, wishlistName);
            ps.executeUpdate();
        }
    }

    @SneakyThrows
    public void resetGiftReservation(String username, String wishlistName, String giftTitle) {
        // Используем is_reserved, а не reserved_by
        String sql = "UPDATE gifts SET is_reserved = false WHERE wishlist_id IN (SELECT id FROM wishlists WHERE user_id = (SELECT id FROM users WHERE username = ?) AND name = ?) AND title = ?";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, wishlistName);
            ps.setString(3, giftTitle);
            ps.executeUpdate();
        }
    }
}