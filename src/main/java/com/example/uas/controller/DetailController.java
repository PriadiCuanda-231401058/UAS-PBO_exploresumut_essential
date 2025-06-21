package com.example.uas.controller;

import com.example.uas.DAO.UlasanDAO;
import com.example.uas.DAO.WisataDAO;
import com.example.uas.model.Ulasan;
import com.example.uas.model.TempatWisata;
import com.example.uas.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

/**
 * Controller for DetailView.fxml.
 * Shows full description, image gallery, reviews, and review submission.
 */
public class DetailController {

    @FXML private Label nameLabel;
    @FXML private Label categoryLabel;
    @FXML private Label locationLabel;
    @FXML private Label infoLabel;
    @FXML private TextArea fullDescriptionArea;
    @FXML private ImageView imageGallery;

    @FXML private ListView<Ulasan> reviewListView;
    @FXML private ComboBox<Integer> ratingComboBox;
    @FXML private TextArea commentArea;

    private int wisataId;
    private WisataDAO wisataDAO = new WisataDAO();
    private UlasanDAO reviewDAO = new UlasanDAO();
    private final User user = new User();

    @FXML
    private void initialize() {
        // Setup rating ComboBox 1-5
        ratingComboBox.getItems().addAll(1, 2, 3, 4, 5);
    }

    /**
     * Set Wisata Id and load detail data.
     */
    public void setWisataId(int id) {
        this.wisataId = id;
        loadWisataDetail();
        loadReviews();
    }

    private void loadWisataDetail() {
        TempatWisata wisata = wisataDAO.getWisataById(wisataId);
        if (wisata == null) return;

        Platform.runLater(() -> {
            nameLabel.setText(wisata.getName());
            categoryLabel.setText("Kategori: " + wisata.getCategory());
            locationLabel.setText("Lokasi: " + wisata.getLocation());

            if(wisata.tampilkanInfo() != null) infoLabel.setText(wisata.tampilkanInfo());

            fullDescriptionArea.setText(wisata.getDescription());

            if (wisata.getImageUrls() != null) {
                for (String url : wisata.getImageUrls()) {
                    if (url != null && !url.isBlank()) {
                        Image imageView = new Image(url.trim(), 150, 100, true, true);
                        imageGallery.setImage(imageView);
                    }
                }
            }
        });
    }

    private void loadReviews() {
        List<Ulasan> reviews = reviewDAO.getReviewsByWisataId(wisataId);
        Platform.runLater(() -> {
            reviewListView.getItems().clear();
            reviewListView.getItems().addAll(reviews);
            reviewListView.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Ulasan item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        String stars = "★".repeat(item.getRating()) + "☆".repeat(5 - item.getRating());
                        String text = String.format("%s\n%s\n%s", item.getUserName(), stars, item.getComment());
                        setText(text);
                    }
                }
            });
        });
    }

    @FXML
    private void onSubmitReview() {
        Integer rating = ratingComboBox.getValue();
        String comment = commentArea.getText();

        if (rating == null) {
            showAlert("Validasi", "Rating harus dipilih.");
            return;
        }
        if (comment == null || comment.isBlank()) {
            showAlert("Validasi", "Komentar tidak boleh kosong.");
            return;
        }

        Ulasan review = new Ulasan();
        review.setId(reviewDAO.getMaxId());
        review.setWisataId(wisataId);
        review.setUserName(user.getLoggedInUsername());
        review.setRating(rating);
        review.setComment(comment);

        boolean success = reviewDAO.insertReview(review);
        if (success) {
            clearReviewForm();
            loadReviews();
            showAlert("Sukses", "Ulasan berhasil ditambahkan.");
        } else {
            showAlert("Error", "Gagal menambahkan ulasan.");
        }
    }

    private void clearReviewForm() {
        ratingComboBox.getSelectionModel().clearSelection();
        commentArea.clear();
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
