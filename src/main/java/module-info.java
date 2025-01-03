module socialmedia.socialmediasimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens socialmedia to javafx.fxml;
    exports socialmedia;
}