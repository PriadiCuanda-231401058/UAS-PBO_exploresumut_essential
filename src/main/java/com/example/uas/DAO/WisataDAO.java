package com.example.uas.DAO;

import com.example.uas.model.TempatWisata;
import com.example.uas.model.WisataAlam;
import com.example.uas.model.WisataBudaya;
import com.example.uas.model.WisataKuliner;
import com.example.uas.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Wisata.
 */
public class WisataDAO {

    private static final Logger LOGGER = Logger.getLogger(WisataDAO.class.getName());

    /**
     * Get all wisata entries sorted descending by id (for newest first).
     */
    public List<TempatWisata> getAllWisata() {
        List<TempatWisata> list = new ArrayList<>();
        String sql = "SELECT id, nama, kategori, lokasi, deskripsi, gambar FROM tempat_wisata ORDER BY id DESC";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nama");
                String category = rs.getString("kategori");
                String location = rs.getString("lokasi");
                String description = rs.getString("deskripsi");
                String imgStr = rs.getString("gambar");
                List<String> images = (imgStr == null || imgStr.isEmpty())
                        ? new ArrayList<>()
                        : Arrays.asList(imgStr.split(","));
                TempatWisata wisata;
                wisata = new TempatWisata(id, name, category, location, description, images);
                list.add(wisata);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Search wisata by filters.
     * All params can be null or empty which means no filtering.
     */
    public List<TempatWisata> searchWisata(String nameFilter, String categoryFilter, String locationFilter) {
        List<TempatWisata> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nama, kategori, lokasi, deskripsi, gambar FROM tempat_wisata WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (nameFilter != null && !nameFilter.isBlank()) {
            sql.append("AND LOWER(name) LIKE ? ");
            params.add("%" + nameFilter.toLowerCase() + "%");
        }
        if (categoryFilter != null && !categoryFilter.isBlank()) {
            sql.append("AND LOWER(category) = ? ");
            params.add(categoryFilter.toLowerCase());
        }
        if (locationFilter != null && !locationFilter.isBlank()) {
            sql.append("AND LOWER(location) LIKE ? ");
            params.add("%" + locationFilter.toLowerCase() + "%");
        }
        sql.append("ORDER BY id DESC");

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TempatWisata w = new TempatWisata();
                    w.setId(rs.getInt("id"));
                    w.setName(rs.getString("nama"));
                    w.setCategory(rs.getString("kategori"));
                    w.setLocation(rs.getString("lokasi"));
                    w.setDescription(rs.getString("deskripsi"));

                    String imgStr = rs.getString("gambar");
                    List<String> images = (imgStr == null || imgStr.isEmpty())
                            ? new ArrayList<>()
                            : Arrays.asList(imgStr.split(","));
                    w.setImageUrls(images);

                    result.add(w);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to search wisata", e);
        }
        return result;
    }

    /**
     * Get a Wisata by ID.
     */
    public TempatWisata getWisataById(int id) {
        TempatWisata w = null;
        String sql = "SELECT id, nama, kategori, lokasi, deskripsi, gambar, info_tambahan FROM tempat_wisata WHERE id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    String category = rs.getString("kategori");
                    String info = rs.getString("info_tambahan");
                    if (category != null) {
                        switch (category.toLowerCase()) {
                            case "alam":
                                w = new WisataAlam(info);
                                break;
                            case "budaya":
                                w = new WisataBudaya(info);
                                break;
                            case "kuliner":
                                w = new WisataKuliner(info);
                                break;
                            default:
                                w = new TempatWisata();
                        }
                    } else {
                        w = new TempatWisata();
                    }
                    w.setId(rs.getInt("id"));
                    w.setName(rs.getString("nama"));
                    w.setCategory(category);
                    w.setLocation(rs.getString("lokasi"));
                    w.setDescription(rs.getString("deskripsi"));

                    // Assuming image_urls stored as comma separated string
                    String imgStr = rs.getString("gambar");
                    List<String> images = (imgStr == null || imgStr.isEmpty())
                            ? new ArrayList<>()
                            : List.of(imgStr.split(","));
                    w.setImageUrls(images);

                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return w;
    }

    /**
     * Get all distinct categories for filtering.
     */
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT kategori FROM tempat_wisata ORDER BY kategori";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(rs.getString("kategori"));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get wisata categories", e);
        }
        return categories;
    }
}
