module com.example.zad5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zad5 to javafx.fxml;
    exports com.example.zad5;
}