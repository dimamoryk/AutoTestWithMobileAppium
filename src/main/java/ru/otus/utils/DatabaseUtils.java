package ru.otus.utils;

import com.google.inject.Singleton;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Singleton
public class DatabaseUtils {

    private final String url = System.getProperty("databaseUrl", "jdbc:postgresql://sql.otus.kartushin.su:5432/wishlist");
    private final String username = System.getProperty("databaseUsername", "student");
    private final String password = System.getProperty("databasePassword", "student");

    @SneakyThrows
    public void prepareWishlistDescription(String login, String description) {
        String sql = "UPDATE wishlists SET description = ? WHERE user_id IN (SELECT id FROM users WHERE username = ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, description);
            ps.setString(2, login);
            ps.executeUpdate();
        }
    }

    @SneakyThrows
    public void cleanGiftsForWishlist(String username, String wishlistName) {
        String sql = "DELETE FROM gifts WHERE wishlist_id IN (SELECT id FROM wishlists WHERE user_id IN (SELECT id FROM users WHERE username = ?) AND name = ?)";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, wishlistName);
            ps.executeUpdate();
        }
    }

    @SneakyThrows
    public void createWishlist(String username, String wishlistName) {
        // Удаляем старый, если есть
        String deleteSql = "DELETE FROM wishlists WHERE user_id IN (SELECT id FROM users WHERE username = ?) AND name = ?";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(deleteSql)) {
            ps.setString(1, username);
            ps.setString(2, wishlistName);
            ps.executeUpdate();
        }
        // Создаём новый
        String insertSql = "INSERT INTO wishlists (user_id, name, description) VALUES ((SELECT id FROM users WHERE username = ?), ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, username);
            ps.setString(2, wishlistName);
            ps.setString(3, "Test list");
            ps.executeUpdate();
        }
    }

    @SneakyThrows
    public void createGift(String username, String wishlistName, String title, String description, String link) {
        String sql = "INSERT INTO gifts (wishlist_id, title, description, link) " +
                "VALUES ((SELECT id FROM wishlists WHERE user_id IN (SELECT id FROM users WHERE username = ?) AND name = ?), ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, wishlistName);
            ps.setString(3, title);
            ps.setString(4, description);
            ps.setString(5, link);
            ps.executeUpdate();
        }
    }

    @SneakyThrows
    public void clearReservation(String username, String wishlistName, String giftTitle) {
        String sql = "UPDATE gifts SET reserved_by = NULL WHERE wishlist_id IN (SELECT id FROM wishlists WHERE user_id IN (SELECT id FROM users WHERE username = ?) AND name = ?) AND title = ?";
        try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, wishlistName);
            ps.setString(3, giftTitle);
            ps.executeUpdate();
        }
    }
}