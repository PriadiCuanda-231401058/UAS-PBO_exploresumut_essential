package com.example.uas.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Function {
    public void moveTo(Node node, String pathFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathFxml));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
//            stage.setFullScreen(true);
            stage.setMaximized(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
