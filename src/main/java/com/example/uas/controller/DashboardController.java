package com.example.uas.controller;

import com.example.uas.DAO.UlasanDAO;
import com.example.uas.DAO.WisataDAO;
import com.example.uas.model.TempatWisata;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.List;

/**
 * Controller for MainView.fxml
 * Handles display & searching/filtering wisata list.
 */
public class DashboardController {

    @FXML private TextField searchField;
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private TextField locationField;
    @FXML private VBox wisataListContainer;

    private WisataDAO wisataDAO;
    UlasanDAO reviewDAO = new UlasanDAO();

    @FXML
    private void initialize() {
        wisataDAO = new WisataDAO();

        // Load categories into ComboBox with "Semua" option
        List<String> categories = wisataDAO.getAllCategories();
        categories.add(0, "Semua");
        categoryComboBox.getItems().addAll(categories);
        categoryComboBox.getSelectionModel().selectFirst();

        loadAllWisata();
    }

    private void loadAllWisata() {
        List<TempatWisata> wisataList = wisataDAO.getAllWisata();
        displayWisataList(wisataList);
    }

    private void displayWisataList(List<TempatWisata> wisataList) {
        wisataListContainer.getChildren().clear();

        if (wisataList.isEmpty()) {
            Label emptyLabel = new Label("Tidak ada destinasi wisata yang ditemukan.");
            wisataListContainer.getChildren().add(emptyLabel);
            return;
        }

        for (TempatWisata w : wisataList) {
            HBox box = createWisataBox(w);
            wisataListContainer.getChildren().add(box);
        }
    }

    private HBox createWisataBox(TempatWisata wisata) {
        double avgRating = reviewDAO.getAverageRatingByWisataId(wisata.getId());
        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #fafafa;");
        box.setSpacing(5);

        ImageView imageView = new ImageView(new Image(wisata.getImageUrls().get(0)));

        imageView.setFitWidth(200);
        imageView.setFitHeight(150);

        String desc = wisata.getDescription();
        if (desc.length() > 100) {
            desc = desc.substring(0, 100) + "...";
        }

        Label nameLabel = new Label(wisata.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        Label ratingLabel = new Label("★ " + String.format("%.1f", avgRating));
        ratingLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #666;");

        Label categoryLabel = new Label("Kategori: " + wisata.getCategory());
        categoryLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #666;");

        Label locationLabel = new Label("Lokasi: " + wisata.getLocation());
        locationLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #666;");

        Text shortDesc = new Text(desc);
        shortDesc.setWrappingWidth(760); // Restrict width for wrapping, adjust based on window size

        box.getChildren().addAll(nameLabel, ratingLabel, categoryLabel, locationLabel, shortDesc);

        HBox itemBox = new HBox(imageView, box);
        itemBox.setPadding(new Insets(10));
        itemBox.setStyle("-fx-border-color: #ccc; -fx-background-color: #f9f9f9;");

        // Click event to open detail page
        itemBox.setOnMouseClicked(event -> openDetailPage(wisata));

        // Change cursor on hover
        itemBox.setOnMouseEntered(event -> box.setStyle("-fx-border-color: #aaa; -fx-background-color: #f0f8ff;"));
        itemBox.setOnMouseExited(event -> box.setStyle("-fx-border-color: #ddd; -fx-background-color: #fafafa;"));

        return itemBox;
    }

    private void openDetailPage(TempatWisata wisata) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/detail.fxml"));
            Parent root = loader.load();

            DetailController controller = loader.getController();
            controller.setWisataId(wisata.getId());

            Stage stage = new Stage();
            stage.setTitle("Detail Wisata: " + wisata.getName());
            stage.setScene(new Scene(root, 700, 500));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal membuka halaman detail wisata.");
        }
    }

    @FXML
    private void onSearch() {
        String name = searchField.getText();
        String category = categoryComboBox.getSelectionModel().getSelectedItem();
        if ("Semua".equals(category)) category = null;
        String location = locationField.getText();

        List<TempatWisata> filtered = wisataDAO.searchWisata(name, category, location);
        displayWisataList(filtered);
    }

    @FXML
    private void onReset() {
        searchField.clear();
        locationField.clear();
        categoryComboBox.getSelectionModel().selectFirst();
        loadAllWisata();
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
