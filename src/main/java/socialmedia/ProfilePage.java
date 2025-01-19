package socialmedia;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private final Stage primaryStage;
    private final UiComponent uiComponent;

    public ProfilePage(User user, Stage primaryStage) {
        this.user = user;
        this.primaryStage = primaryStage;
        this.uiComponent = new UiComponent(primaryStage);
        initialize();
    }

    private void initialize() {
        rootLayout = uiComponent.createStackPane("stack-pane");

        HBox mainLayout = uiComponent.createHBox("root-layout", 0);

        VBox profileSection = createProfileSection();
        profileSection.setPrefWidth(700);

        ScrollPane postsSection = createPostsSection();
        HBox.setHgrow(postsSection, Priority.ALWAYS);

        mainLayout.getChildren().addAll(profileSection, postsSection);
        assert rootLayout != null;
        rootLayout.getChildren().add(mainLayout);
    }

    public Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private VBox createProfileSection() {
        VBox profileSection = uiComponent.createVBox("profile-section", 15);

        profilePicture = new ImageView(user.getProfilePicture() != null ? user.getProfilePicture() : new Image("file:icons/profile.jpg", 200, 200, true, true));
        profilePicture.setFitWidth(200);
        profilePicture.setFitHeight(200);
        profilePicture.setClip(new Circle(100, 100, 100));

        Label name = uiComponent.createLabel(user.getFullName(), "name", 0.0);
        Label username = uiComponent.createLabel("@" + user.getUsername(), "username", 0.0);
        Label bio = uiComponent.createLabel(user.getBio() != null ? user.getBio() : "No bio available.", "bio", 0.0);
        bio.setWrapText(true);

        Button backButton = uiComponent.createButton("Back to Home", "profile-button", this::navigateBackToHome);

        if (userManager.getCurrentUser().getUsername().equals(user.getUsername())) {

            Button editButton = uiComponent.createButton("Edit Profile", "profile-button", this::openEditProfilePage);
            profilePicture.setOnMouseClicked(event -> changeProfilePicture());
            profileSection.getChildren().addAll(profilePicture, name, username, bio, editButton, backButton);

        } else {
            ToggleButton favoriteToggle = uiComponent.createToggleButton("favorite-toggle");
            boolean selected = userManager.getCurrentUser().getFavoriteUsersUsername().contains(user.getUsername());

            if(selected) {
                favoriteToggle.setText("Remove from Favorites");
            } else {
                favoriteToggle.setText("Add to Favorites");
            }

            favoriteToggle.setOnAction(event -> {
                if (selected) {
                 favoriteToggle.setText("Add to Favorites");
                   userManager.getCurrentUser().removeFromFavoriteUsers(user);
                 } else {
                    favoriteToggle.setText("Remove from Favorites");
                    userManager.getCurrentUser().addToFavoriteUsers(user);
            }
         });

            profileSection.getChildren().addAll(profilePicture, name, username, bio, favoriteToggle, backButton);
        }
        return profileSection;
    }

    private ScrollPane createPostsSection() {
        ScrollPane scrollPane = uiComponent.createScrollPane("posts-section-scrollpane");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        VBox postsContainer = uiComponent.createVBox("posts-container", 0);

        if (user.getPosts().isEmpty()) {
            Label noPostsLabel = uiComponent.createLabel("This user has no posts.", "no-posts-label", 0.0);
            postsContainer.getChildren().add(noPostsLabel);
        } else {
            for (Post post : user.getPosts()) {
                postsContainer.getChildren().add(createPost("@"+post.getAuthor()+" "+post.getContent()));
            }
        }

        scrollPane.setContent(postsContainer);
        return scrollPane;
    }

    private VBox createPost(String content) {
        VBox postBox = uiComponent.createVBox("post-box", 0);

        Label postContent = uiComponent.createLabel(content, "post-content", 0.0);
        postContent.setWrapText(true);

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
            profilePicture.setFitWidth(200);
            profilePicture.setFitHeight(200);
            profilePicture.setClip(new Circle(100, 100, 100));
            user.setProfilePicture(newProfileImage);
        }
    }

    private void openEditProfilePage() {
        EditProfilePage editProfilePage = new EditProfilePage(user, primaryStage);
        primaryStage.setScene(editProfilePage.getScene());
    }

    private void navigateBackToHome() {
        HomePage homePage = new HomePage();
        homePage.start(primaryStage);
    }
}