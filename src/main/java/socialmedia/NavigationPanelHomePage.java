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


        Button aboutButton = uiComponent.createButton("About", "nav-item", () -> {
            AboutPage aboutPage = new AboutPage();
            aboutPage.start(primaryStage);
        });

        Button LogOutButton = uiComponent.createButton("Log Out", "nav-item", () -> {
            userManager.setCurrentUser(null);
            LoginPage loginPage = new LoginPage();
            loginPage.start(primaryStage);
        });

        Button profileButton = uiComponent.createButton("Profile", "nav-item", () -> {
            ProfilePage profilePage = new ProfilePage(userManager.getCurrentUser(), primaryStage);
            primaryStage.setScene(profilePage.getScene());
        });

        this.getChildren().addAll(profileButton, aboutButton, LogOutButton);
    }
}