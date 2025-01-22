package socialmedia;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AboutPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox rootLayout = new VBox(20); // Create root layout using VBox
        rootLayout.getStyleClass().add("root-layout");

        Button backButton = createBackButton(primaryStage);
        rootLayout.getChildren().add(backButton);

        // Adds profile section for the group members
        String[] groupMembers = {"Leul Teferi", "Leul Zegeye", "Natnael Solomon", "Nebiyu Samuel"};
        String[] memberRoles = {
                "Coordinated the project and managed team communication.",
                "Worked on both frontend and backend.",
                "Responsible for the user interface and design.",
                "Focused on design and implementation of data models"
        };

        for (int i = 0; i < groupMembers.length; i++) {
            String memberName = groupMembers[i];
            String roleDescription = memberRoles[i];

            VBox profileSection = createProfileSection(memberName, roleDescription);
            profileSection.setMaxSize(600,100);
            rootLayout.getChildren().add(profileSection);
        }
        rootLayout.setAlignment(Pos.CENTER);


        ScrollPane root = new ScrollPane(rootLayout);
        root.setFitToWidth(true);
        Scene scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");


        primaryStage.setScene(scene);
        primaryStage.setTitle("About Page");
        primaryStage.show();
    }

    private Button createBackButton(Stage primaryStage) {
        Button backButton = new Button("Back to Home");
        backButton.getStyleClass().add("edit-profile-button");

        backButton.setOnAction(event -> {
            HomePage homePage = new HomePage();
            homePage.start(primaryStage);
        });

        return backButton;
    }

    private VBox createProfileSection(String usernameText, String role) {
        VBox profileSection = new VBox(10);
        profileSection.getStyleClass().add("profile-section");

        // Profile picture
        ImageView profilePicture = new ImageView(new Image("file:icons/profile.jpg", 100, 100, true, true));
        profilePicture.getStyleClass().add("profile-picture");
        profilePicture.setClip(new Circle(50, 50, 50)); // Circular clip for profile picture

        // User info
        Label username = new Label(usernameText);
        username.getStyleClass().add("username");

        Label bio = new Label(role);
        bio.getStyleClass().add("bio");

        profileSection.getChildren().addAll(profilePicture, username, bio);
        return profileSection;
    }
}
