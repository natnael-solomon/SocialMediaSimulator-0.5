package socialmedia;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class FavoriteHomePage extends VBox {

    public FavoriteHomePage() {
        this.setSpacing(15);
        this.getStyleClass().add("favorite");


        Label title = new Label("Favorite Users");
        title.getStyleClass().add("favorite-title");
        this.getChildren().add(title);


        for (int i = 1; i <= 5; i++) {
            Label trendItem = new Label("@username" + i);
            trendItem.getStyleClass().add("favorite-user");
            this.getChildren().add(trendItem);
        }
    }
}
