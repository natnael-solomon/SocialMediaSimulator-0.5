package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class HeaderHomePage extends HBox {

    public HeaderHomePage() {
        this.setSpacing(20);
        this.getStyleClass().add("header");
        this.setAlignment(Pos.CENTER_LEFT);

        this.getChildren().addAll(
                createLabel("Simple Social Media Simulator", "logo"),
                createLabel("🔍 Search ", "search-bar"),
                createLabel("👤", "profile-icon")
        );
    }

    private Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }
}
