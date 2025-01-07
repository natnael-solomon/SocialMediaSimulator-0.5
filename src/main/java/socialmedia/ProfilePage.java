package socialmedia;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class ProfilePage {

    private final HBox rootLayout;

    public ProfilePage() {
        rootLayout = new HBox();
        rootLayout.setPadding(new Insets(20));
        rootLayout.setSpacing(20);
        rootLayout.setStyle("-fx-background-color: #f5f5f5;");

        // Add profile section on the left
        VBox profileSection = createProfileSection();
        profileSection.setPrefWidth(300); // Fixed width for the profile section

        // Add posts section on the right
        ScrollPane postsSection = createPostsSection();
        HBox.setHgrow(postsSection, Priority.ALWAYS); // Allow posts section to grow

        rootLayout.getChildren().addAll(profileSection, postsSection);
    }

    public Scene getScene() {
        return new Scene(rootLayout, 1200, 700);
    }

    private VBox createProfileSection() {
        VBox profileSection = new VBox();
        profileSection.setSpacing(15);
        profileSection.setPadding(new Insets(10));
        profileSection.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 10;");
        profileSection.setAlignment(Pos.TOP_CENTER);

        // Profile picture
        ImageView profilePicture = new ImageView(new Image("file:icons/icon512.png", 100, 100, true, true));
        profilePicture.setStyle("-fx-border-color: #ddd; -fx-border-width: 2px; -fx-border-radius: 50px;");
        profilePicture.setClip(new Circle(50, 50, 50)); // Circular clip for profile picture

        // User info
        Label username = new Label("Username");
        username.setFont(Font.font("Arial", 20));
        username.setTextFill(Color.BLACK);

        Label bio = new Label("This is the user's bio. It can span multiple lines and includes brief details about the user.");
        bio.setWrapText(true);
        bio.setTextFill(Color.GRAY);
        bio.setMaxWidth(250);

        HBox stats = new HBox(20);
        stats.setAlignment(Pos.CENTER);


        ToggleButton favoriteToggle = new ToggleButton("Add to Favorites");
        favoriteToggle.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
        favoriteToggle.setSelected(false);  // Initial state (not favorited)

        favoriteToggle.setOnAction(event -> {
            if (favoriteToggle.isSelected()) {
                favoriteToggle.setText("Remove from Favorites");
                // Handle adding the user to favorites (e.g., update database or internal state)
            } else {
                favoriteToggle.setText("Add to Favorites");
                // Handle removing the user from favorites
            }
        });

        profileSection.getChildren().addAll(profilePicture, username, bio, stats, favoriteToggle);
        return profileSection;
    }

    private ScrollPane createPostsSection() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-padding: 10;");

        VBox postsContainer = new VBox();
        postsContainer.setSpacing(15);
        postsContainer.setPadding(new Insets(10));
        postsContainer.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 10;");


        // Add sample posts
        List<String> samplePosts = List.of(
                "This is a sample post #1.",
                "Enjoying a great day at the park!",
                "Check out this amazing JavaFX UI design!",
                "Here's a random thought for today...",
                "Another post to showcase layout."
        );

        for (String post : samplePosts) {
            postsContainer.getChildren().add(createPost(post));
        }

        scrollPane.setContent(postsContainer);
        return scrollPane;
    }

    private VBox createPost(String content) {
        VBox postBox = new VBox();
        postBox.setPadding(new Insets(10));
        postBox.setSpacing(10);
        postBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ddd; -fx-border-radius: 8;");

        Label postContent = new Label(content);
        postContent.setWrapText(true);
        postContent.setFont(Font.font("Arial", 14));
        postContent.setTextFill(Color.BLACK);

        postBox.getChildren().add(postContent);
        return postBox;
    }

    public static void main(String[] args) {
        // Launch the JavaFX application for testing the ProfilePage
        javafx.application.Application.launch(ProfilePageApp.class);
    }

    public static class ProfilePageApp extends javafx.application.Application {
        @Override
        public void start(Stage primaryStage) {
            ProfilePage profilePage = new ProfilePage();
            primaryStage.setScene(profilePage.getScene());
            primaryStage.setTitle("Profile Page");
            primaryStage.show();
        }
    }
}
