module socialmedia.socialmediasimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens socialmedia to javafx.fxml;
    exports socialmedia;
}

