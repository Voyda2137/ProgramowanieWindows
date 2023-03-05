module com.example.zad2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zad2 to javafx.fxml;
    exports com.example.zad2;
}