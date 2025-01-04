package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class HeaderHomePage extends HBox {

    public HeaderHomePage() {
        this.setSpacing(20);
        this.getStyleClass().add("header");
        this.setAlignment(Pos.CENTER_LEFT);

        this.getChildren().addAll(
                createLabel("Simple Social Media Simulator", "logo"),
                createTextField("🔍 Search ", "search-bar")
        );
    }

    private Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }
    private TextField createTextField(String text, String styleClass) {
        TextField textField = new TextField();
        textField.setPromptText(text);
        textField.getStyleClass().add(styleClass);
        textField.setFocusTraversable(false);
        return textField;
    }
}
