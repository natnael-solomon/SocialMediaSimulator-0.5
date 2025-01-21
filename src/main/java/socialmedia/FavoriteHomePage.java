package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import static socialmedia.Main.userManager;

public class FavoriteHomePage extends ScrollPane {

    public FavoriteHomePage(Stage primaryStage) {

        VBox content = new VBox();
        content.getStyleClass().add("favorite");
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.getStyleClass().add("favorite-scroll-pane");

        Label title = new Label("Favorite Users");
        title.getStyleClass().add("favorite-title");
        content.getChildren().add(title);

        List<String> favoriteUsersUsername = userManager.getCurrentUser().getFavoriteUsersUsername();

        for (String username : favoriteUsersUsername) {

            Button trendItem = new Button(userManager.findUserByUsername(username).getFullName());
            trendItem.getStyleClass().add("favorite-user");

            trendItem.setOnMouseClicked(event -> {
                ProfilePage profilePage = new ProfilePage(userManager.findUserByUsername(username), primaryStage);
                primaryStage.setScene(profilePage.getScene());
            });

            content.getChildren().add(trendItem);
        }


        if (favoriteUsersUsername.isEmpty()) {
            Label noFavorites = new Label("You have no favorite users.");
            noFavorites.getStyleClass().add("favorite-user");
            content.getChildren().add(noFavorites);
        }

        this.setContent(content);
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }
}