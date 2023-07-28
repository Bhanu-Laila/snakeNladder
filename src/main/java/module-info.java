module com.example.snakenladder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.snakenladder to javafx.fxml;
    exports com.example.snakenladder;
}