package com.example.systemgestiondesdettefournisseurs;

import com.example.systemgestiondesdettefournisseurs.Models.FournisseurData;
import com.example.systemgestiondesdettefournisseurs.Models.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author BakJunior
 * My email and number --> bakbassjunior@gmail.com (+225 0758084992)
 * Thanks!
 */
public class ControllerDashboard implements Initializable {

    @FXML
    private AnchorPane main_form;


    @FXML
    private AnchorPane addFournisseur_form;

    @FXML
    private Button Dettes_btn;

    @FXML
    private Button home_btn;

    @FXML
    private Button ReglementDette_btn;

    @FXML
    private Button Fournisseur_btn;


    @FXML
    private Button close;


    @FXML
    private Button minus;

    @FXML
    private TextField AdresseFourn;


    @FXML
    private TextField CategoriesFourn;
    @FXML
    private TableView<FournisseurData> TableView_Fourn;

    @FXML
    private TableColumn<FournisseurData, String> Colum_AdresseFourn;

    @FXML
    private TableColumn<FournisseurData, String> Colum_CategoriesFourn;

    @FXML
    private TableColumn<FournisseurData, String> Colum_EmailFourn;

    @FXML
    private TableColumn<FournisseurData, String> Colum_NomFourn;

    @FXML
    private TableColumn<FournisseurData, String> Colum_TelephoneFourn;

    @FXML
    private TextField EmailFourn;

    @FXML
    private TextField NomFourn;

    @FXML
    private TextField TelephoneFourn;

    @FXML
    private Button logout;

    //Variable de Traitement base de donnée
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public ControllerDashboard() {
    }

    public void EnregistrerFourn(ActionEvent actionEvent) {

        String sql = "INSERT INTO fournisseurs"
                + "(Nom,Adresse,Telephone,Categories,Email) "
                + "VALUES(?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (NomFourn.getText().isEmpty()
                    || CategoriesFourn.getText().isEmpty()
                    || TelephoneFourn.getText().isEmpty()
                    || AdresseFourn.getText().isEmpty()
                    || EmailFourn.getText().isEmpty()
                   ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, NomFourn.getText());
                prepare.setString(2, AdresseFourn.getText());
                prepare.setString(3, (String) TelephoneFourn.getText());
                prepare.setString(4, CategoriesFourn.getText());
                prepare.setString(5, EmailFourn.getText());
                prepare.executeUpdate();

                EnregistrerFournShowListData();
                EnregistrerFournReset();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<FournisseurData> EnregistrerFournListData() {

        ObservableList<FournisseurData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM fournisseurs ORDER BY ID_fournisseur DESC";
        ;

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            FournisseurData FournisseurD;

            while (result.next()) {
                FournisseurD = new FournisseurData(result.getString("nom"),
                        result.getString("adresse"),
                        result.getString("telephone"),
                        result.getString("categories"),
                        result.getString("email"));


                listData.add(FournisseurD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<FournisseurData> EnregistrerFournList;

    public void EnregistrerFournShowListData() {
        EnregistrerFournList = EnregistrerFournListData();

        Colum_NomFourn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Colum_AdresseFourn.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        Colum_TelephoneFourn.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
        Colum_CategoriesFourn.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        Colum_EmailFourn.setCellValueFactory(new PropertyValueFactory<>("Email"));


        TableView_Fourn.setItems(EnregistrerFournList);

    }

    public void EnregistrerFournSelect() {
        FournisseurData FournisseurD = TableView_Fourn.getSelectionModel().getSelectedItem();
        int num = TableView_Fourn.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        NomFourn.setText(FournisseurD.getNom());
        AdresseFourn.setText(FournisseurD.getAdresse());
        CategoriesFourn.setText(FournisseurD.getCategories());
        TelephoneFourn.setText(String.valueOf(FournisseurD.getTelephone()));
        EmailFourn.setText(FournisseurD.getEmail());

    }

    public void EnregistrerFournReset() {
        NomFourn.setText("");
        AdresseFourn.setText("");
        TelephoneFourn.setText("");
        EmailFourn.setText("");
        CategoriesFourn.setText("");

    }

    public void EnregistrerFournDelete() {

        String sql = "DELETE FROM fournisseurs WHERE nom = '"
                + NomFourn.getText() + "'";


        connect = database.connectDb();

        try {

            Alert alert;
            if (NomFourn.getText().isEmpty()
                    || CategoriesFourn.getText().isEmpty()
                    || TelephoneFourn.getText().isEmpty()
                    || AdresseFourn.getText().isEmpty()
                    || EmailFourn.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Etre vous sur de vouloir supprimer le fournisseur : " + NomFourn.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Supprimé avec succès!");
                    alert.showAndWait();

                    EnregistrerFournShowListData();
                    EnregistrerFournReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void EnregistrerFournUpdate() {

        String sql = "UPDATE fournisseur SET nom = ? , Adresse = ? , Telephone = ? , Categorie = ? , Email = ?";
        connect = database.connectDb();

        try {
            Alert alert;
            if (NomFourn.getText().isEmpty()
                    || CategoriesFourn.getText().isEmpty()
                    || TelephoneFourn.getText().isEmpty()
                    || AdresseFourn.getText().isEmpty()
                    || EmailFourn.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs vides");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir MODIFIER LES DONNEES DU FOURNISSEUR: " + NomFourn.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Mise à jour réussie!");
                    alert.showAndWait();


                    EnregistrerFournShowListData();
                    EnregistrerFournReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private double x = 0;
    private double y = 0;

    public void btn_logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource() == home_btn) {

            addFournisseur_form.setVisible(false);


            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2d2d38, #7a697e);");
            Fournisseur_btn.setStyle("-fx-background-color:transparent");
            Dettes_btn.setStyle("-fx-background-color:transparent");
            ReglementDette_btn.setStyle("-fx-background-color:transparent");

            /*homeTotalEmployees();
            homeEmployeeTotalPresent();
            homeTotalInactive();
            homeChart();*/

        } else if (actionEvent.getSource() == Fournisseur_btn) {
            addFournisseur_form.setVisible(true);


            Fournisseur_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2d2d38, #7a697e);");
            home_btn.setStyle("-fx-background-color:transparent");

            //addEmployeeGendernList();
            //addEmployeePositionList();
            //addEmployeeSearch();

        } else if (actionEvent.getSource() == Dettes_btn) {

            main_form.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Dette.fxml"));
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
    }

    @FXML
    void btn_close(ActionEvent event) {System.exit(0);}

    @FXML
    void btn_minus(ActionEvent event) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);

    }











    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EnregistrerFournShowListData();

    }



}
