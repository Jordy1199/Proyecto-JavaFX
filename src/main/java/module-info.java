module com.example.gestionnotasjavax {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestionnotasjavax to javafx.fxml;
    exports com.example.gestionnotasjavax;
}