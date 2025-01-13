package socialmedia;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static socialmedia.Main.userManager;

public class ProfilePage {

    private StackPane rootLayout;
    private final User user;
    private ImageView profilePicture;
    private VBox profileSection;

    public ProfilePage(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        rootLayout = new StackPane();
        rootLayout.getStyleClass().add("stack-pane");

        HBox mainLayout = new HBox();
        mainLayout.getStyleClass().add("root-layout");

        profileSection = createProfileSection();
        profileSection.setPrefWidth(700);

        // Add posts section on the right
        ScrollPane postsSection = createPostsSection();
        HBox.setHgrow(postsSection, Priority.ALWAYS);

        mainLayout.getChildren().addAll(profileSection, postsSection);
        rootLayout.getChildren().add(mainLayout);
    }

    public Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private VBox createProfileSection() {
        UiComponent uiComponent = new UiComponent(new Stage());
        VBox profileSection = uiComponent.createVBox("profile-section", 15);

        if (user.getProfilePicture() != null) {
            profilePicture = new ImageView(user.getProfilePicture());
        } else {
            profilePicture = new ImageView(new Image("file:icons/profile.jpg", 400, 400, true, true));
        }

      profilePicture = new ImageView(user.getProfilePicture() != null ? user.getProfilePicture()
                : new Image("file:icons/profile.jpg", 200, 200, true, true));
        profilePicture.setFitWidth(200);
        profilePicture.setFitHeight(200);
        profilePicture.setClip(new Circle(100, 100, 100));
        profilePicture.setOnMouseClicked(event -> changeProfilePicture());

        
        // User info
        Label name = uiComponent.createLabel(user.getFullName(), "name", 0.0); // Add name label
        Label username = uiComponent.createLabel(user.getUsername(), "username", 0.0);
        Label bio = uiComponent.createLabel(user.getBio() != null ? user.getBio() : "No bio available.", "bio", 0.0);
        bio.setWrapText(true);

        
        if (userManager.getCurrentUser().getUsername().equals(user.getUsername())) {
            // Adds edit button
            ToggleButton editButton = uiComponent.createToggleButton("Edit Profile", "edit-button");
            editButton.setOnAction(event -> { openEditProfilePage(); });

            profileSection.getChildren().addAll(profilePicture, name, username, bio, editButton);
        } else {
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

        profileSection.getChildren().addAll(profilePicture, name, username, bio, favoriteToggle); 
    }
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

    private void changeProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(profilePicture.getScene().getWindow());
        if (selectedFile != null) {
            Image newProfileImage = new Image(selectedFile.toURI().toString(), 400, 400, true, true);

            profilePicture.setImage(newProfileImage);
            profilePicture.setFitWidth(400); // Set the fit width
            profilePicture.setFitHeight(400); // Set the fit height
            profilePicture.setClip(new Circle(200, 200, 100)); // Circular clip for profile picture
            profilePicture.setOnMouseClicked(event -> changeProfilePicture());
            user.setProfilePicture(newProfileImage); // Ensure User class has this method
        }
    }

    private void updateProfileSection() {
        profileSection.getChildren().clear();
        profileSection.getChildren().add(createProfileSection());
    }

    private void openEditProfilePage() {
        EditProfilePage editProfilePage = new EditProfilePage(user);
        Stage stage = new Stage();
        stage.setScene(editProfilePage.getScene());
        stage.setOnHidden(event -> updateProfileSection()); // Update profile section when edit window is closed
        stage.show();
    }
}