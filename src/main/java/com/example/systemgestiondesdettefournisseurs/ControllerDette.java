package com.example.systemgestiondesdettefournisseurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDette implements Initializable {

    @FXML
    private AnchorPane Dette_form;

    private double x = 0;
    private double y = 0;
    @FXML
    void Btn_RetourAccueil(ActionEvent actionEvent) throws IOException {

        Dette_form.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }

    public void EnregistrerDette(ActionEvent actionEvent) {
    }

    public void EnregistrerDetteReset(ActionEvent actionEvent) {
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
