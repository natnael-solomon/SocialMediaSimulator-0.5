package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static socialmedia.Main.userManager;

public class NavigationPanelHomePage extends VBox {

    public NavigationPanelHomePage(Stage primaryStage) {
        UiComponent uiComponent = new UiComponent(primaryStage);
        this.setSpacing(15);
        this.getStyleClass().add("nav-panel");

        Button homeButton = uiComponent.createButton("Home", "nav-item", null);
        Button exploreButton = uiComponent.createButton("Explore", "nav-item", null);
        Button notificationsButton = uiComponent.createButton("Notifications", "nav-item", null);
        Button messagesButton = uiComponent.createButton("Messages", "nav-item", null);
        Button profileButton = uiComponent.createButton("Profile", "nav-item", () -> {
            ProfilePage profilePage = new ProfilePage(userManager.getCurrentUser(), primaryStage);
            primaryStage.setScene(profilePage.getScene());
        });

        this.getChildren().addAll(homeButton, exploreButton, notificationsButton, messagesButton, profileButton);
    }
}