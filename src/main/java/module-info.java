module socialmedia.socialmediasimulator {
    requires javafx.controls;
    requires javafx.fxml;
 


    opens socialmedia to javafx.fxml;
    exports socialmedia;
}

