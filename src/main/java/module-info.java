module com.example.systemgestiondesdettefournisseurs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.systemgestiondesdettefournisseurs to javafx.fxml;
    exports com.example.systemgestiondesdettefournisseurs;
    exports com.example.systemgestiondesdettefournisseurs.Models;
    opens com.example.systemgestiondesdettefournisseurs.Models to javafx.fxml;
}