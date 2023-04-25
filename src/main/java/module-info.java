module com.example.welovetetris {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.welovetetris to javafx.fxml;
    exports com.example.welovetetris;
}