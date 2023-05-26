module com.example.xoxoxoxoxo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.XO to javafx.fxml;
    exports com.example.XO;
}