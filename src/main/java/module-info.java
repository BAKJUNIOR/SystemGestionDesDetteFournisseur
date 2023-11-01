module com.example.systemgestiondesdettefournisseurs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.systemgestiondesdettefournisseurs to javafx.fxml;
    exports com.example.systemgestiondesdettefournisseurs;
}