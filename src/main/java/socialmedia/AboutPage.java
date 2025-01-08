package socialmedia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AboutPage {

    private static final HBox rootLayout = new HBox();

    public AboutPage() {
        rootLayout.getStyleClass().add("root-layout");

        // Add four profile sections side by side
        for (int i = 0; i < 4; i++) {
            VBox profileSection = createProfileSection("Developer " + (i + 1), "This is developer " + (i + 1) + "'s bio.");
            profileSection.setPrefWidth(300); // Adjusted width for each profile section
            rootLayout.getChildren().add(profileSection);
        }
    }

    public static Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private VBox createProfileSection(String usernameText, String bioText) {
        VBox profileSection = new VBox();
        profileSection.getStyleClass().add("profile-section");

        // Profile picture
        ImageView profilePicture = new ImageView(new Image("file:icons/icon512.png", 100, 100, true, true));
        profilePicture.getStyleClass().add("profile-picture");
        profilePicture.setClip(new Circle(50, 50, 50)); // Circular clip for profile picture

        // User info
        Label username = new Label(usernameText);
        username.getStyleClass().add("username");

        Label bio = new Label(bioText);
        bio.getStyleClass().add("bio");

        profileSection.getChildren().addAll(profilePicture, username, bio);
        return profileSection;
    }

    public static void main(String[] args) {
        // Launch the JavaFX application for testing the AboutPage
        Application.launch(AboutPageApp.class);
    }

    public static class AboutPageApp extends Application {
        @Override
        public void start(Stage primaryStage) {
            AboutPage aboutPage = new AboutPage();
            primaryStage.setScene(AboutPage.getScene());
            primaryStage.setTitle("About Page");
            primaryStage.show();
        }
    }
}