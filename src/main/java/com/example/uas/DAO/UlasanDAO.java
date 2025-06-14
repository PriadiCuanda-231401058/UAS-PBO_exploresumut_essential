package com.example.uas.DAO;

import com.example.uas.database.DBConnection;
import com.example.uas.model.Ulasan;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for Review/Ulasan.
 */
public class UlasanDAO {
    private static final Logger LOGGER = Logger.getLogger(UlasanDAO.class.getName());

    /**
     * Get all reviews for a wisata by wisataId.
     */
    public List<Ulasan> getReviewsByWisataId(int wisataId) {
        List<Ulasan> reviews = new ArrayList<>();
        String sql = "SELECT id, wisata_id, user_name, rating, comment, review_time FROM review WHERE wisata_id = ? ORDER BY review_time DESC";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, wisataId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Ulasan r = new Ulasan();
                    r.setId(rs.getInt("id"));
                    r.setWisataId(rs.getInt("wisata_id"));
                    r.setUserName(rs.getString("user_name"));
                    r.setRating(rs.getInt("rating"));
                    r.setComment(rs.getString("comment"));
                    Timestamp ts = rs.getTimestamp("review_time");
                    if (ts != null) r.setReviewTime(ts.toLocalDateTime());
                    reviews.add(r);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get reviews for wisata id: " + wisataId, e);
        }
        return reviews;
    }

    /**
     * Insert a new review.
     */
    public boolean insertReview(Ulasan review) {
        String sql = "INSERT INTO review (wisata_id, user_name, rating, comment, review_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, review.getWisataId());
            pstmt.setString(2, review.getUserName());
            pstmt.setInt(3, review.getRating());
            pstmt.setString(4, review.getComment());
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            int affected = pstmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to insert review", e);
            return false;
        }
    }
}
