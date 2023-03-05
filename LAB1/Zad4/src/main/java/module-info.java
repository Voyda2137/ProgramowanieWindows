module com.example.zad4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zad4 to javafx.fxml;
    exports com.example.zad4;
}