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


public class ProfilePage {

    private final HBox rootLayout;
    private final User user;

    public ProfilePage(User user) {
        this.user = user;
        rootLayout = new HBox();
        rootLayout.getStyleClass().add("root-layout");

        // Add profile section on the left
        VBox profileSection = createProfileSection();
        profileSection.setPrefWidth(400);

        // Add posts section on the right
        ScrollPane postsSection = createPostsSection();
        HBox.setHgrow(postsSection, Priority.ALWAYS);

        rootLayout.getChildren().addAll(profileSection, postsSection);
    }

    public Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private VBox createProfileSection() {
        UiComponent uiComponent = new UiComponent(new Stage());
        VBox profileSection = uiComponent.createVBox("profile-section", 15);


        ImageView profilePicture;
        if (user.getProfilePicture() != null) {
            profilePicture = new ImageView(user.getProfilePicture());
        } else {
            profilePicture = new ImageView(new Image("file:icons/profile.jpg", 400, 400,true, true));

        }

        profilePicture.setClip(new Circle(200, 200, 100)); // Circular clip for profile picture


        // User info
        Label username = uiComponent.createLabel(user.getUsername(), "username", 0.0);
        Label bio = uiComponent.createLabel(user.getBio() != null ? user.getBio() : "No bio available.", "bio", 0.0);
        bio.setWrapText(true);

        ToggleButton favoriteToggle = uiComponent.createToggleButton("Add to Favorites", "favorite-toggle");
        favoriteToggle.setSelected(user.getFavoriteUsers().contains(user)); // Check if the user is already a favorite

        favoriteToggle.setOnAction(event -> {
            if (favoriteToggle.isSelected()) {
                favoriteToggle.setText("Remove from Favorites");
                // Add to favorites
                // Handle adding this user to the current user's favorites
            } else {
                favoriteToggle.setText("Add to Favorites");
                // Remove from favorites
                // Handle removing this user from the current user's favorites
            }
        });

        profileSection.getChildren().addAll(profilePicture, username, bio, favoriteToggle);
        return profileSection;
    }

    private ScrollPane createPostsSection() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("posts-section-scrollpane");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        VBox postsContainer = new VBox();
        postsContainer.getStyleClass().add("posts-container");

        // Add user's posts
        if (user.getPosts().isEmpty()) {
            Label noPostsLabel = new Label("This user has no posts.");
            noPostsLabel.getStyleClass().add("no-posts-label");
            postsContainer.getChildren().add(noPostsLabel);
        } else {
            for (Post post : user.getPosts()) {
                postsContainer.getChildren().add(createPost(post.getContent()));
            }
        }

        scrollPane.setContent(postsContainer);
        return scrollPane;
    }

    private VBox createPost(String content) {
        VBox postBox = new VBox();
        postBox.getStyleClass().add("post-box");

        Label postContent = new Label(content);
        postContent.setWrapText(true);
        postContent.getStyleClass().add("post-content");

        postBox.getChildren().add(postContent);
        return postBox;
    }

}
