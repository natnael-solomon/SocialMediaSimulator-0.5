package socialmedia;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.List;

public class ProfilePage {

    private final HBox rootLayout;

    public ProfilePage() {
        rootLayout = new HBox();
        rootLayout.getStyleClass().add("root-layout");

        // Add profile section on the left
        VBox profileSection = createProfileSection();
        profileSection.setPrefWidth(300); // Fixed width for the profile section

        // Add posts section on the right
        ScrollPane postsSection = createPostsSection();
        HBox.setHgrow(postsSection, Priority.ALWAYS); // Allow posts section to grow

        rootLayout.getChildren().addAll(profileSection, postsSection);
    }

    public Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private VBox createProfileSection() {
        VBox profileSection = new VBox();
        profileSection.getStyleClass().add("profile-section");

        // Profile picture
        ImageView profilePicture = new ImageView(new Image("file:icons/icon512.png", 100, 100, true, true));
        profilePicture.getStyleClass().add("profile-picture");
        profilePicture.setClip(new Circle(50, 50, 50)); // Circular clip for profile picture

        // User info
        Label username = new Label("Username");
        username.getStyleClass().add("username");

        Label bio = new Label("This is the user's bio. It can span multiple lines and includes brief details about the user.");
        bio.getStyleClass().add("bio");

        ToggleButton favoriteToggle = new ToggleButton("Add to Favorites");
        favoriteToggle.getStyleClass().add("favorite-toggle");
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

        profileSection.getChildren().addAll(profilePicture, username, bio, favoriteToggle);
        return profileSection;
    }

    private ScrollPane createPostsSection() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-padding: 10;");

        VBox postsContainer = new VBox();
        postsContainer.getStyleClass().add("posts-container");

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
        postBox.getStyleClass().add("post-box");

        Label postContent = new Label(content);
        postContent.getStyleClass().add("post-content");

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