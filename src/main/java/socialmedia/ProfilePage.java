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
import javafx.stage.Stage;

import static socialmedia.Main.userManager;

public class ProfilePage {

    private StackPane rootLayout;
    private final User user;
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

        ImageView profilePicture = createProfilePicture();
        Label name = uiComponent.createLabel(user.getFullName(), "name", 0.0);
        Label username = uiComponent.createLabel("@" + user.getUsername(), "username", 0.0);
        Label bio = uiComponent.createLabel(user.getBio() != null ? user.getBio() : "No bio available.", "bio", 0.0);
        bio.setWrapText(true);

        Button backButton = uiComponent.createButton("Back to Home", "profile-button", this::navigateBackToHome);

        if (isCurrentUserProfile()) {
            Button editButton = uiComponent.createButton("Edit Profile", "profile-button", this::openEditProfilePage);
            profileSection.getChildren().addAll(profilePicture, name, username, bio, editButton, backButton);
        } else {
            ToggleButton favoriteToggle = createFavoriteToggle();
            profileSection.getChildren().addAll(profilePicture, name, username, bio, favoriteToggle, backButton);
        }
        return profileSection;
    }

    private ImageView createProfilePicture() {
        String profileImagePath = user.getProfilePicturePath();
        Image profileImage = profileImagePath != null ? new Image(profileImagePath) : new Image("file:icons/profile.jpg", 200, 200, true, true);
        ImageView imageView = new ImageView(profileImage);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setClip(new Circle(100, 100, 100));
        return imageView;
    }

    private ToggleButton createFavoriteToggle() {
        ToggleButton favoriteToggle = uiComponent.createToggleButton("favorite-toggle");
        boolean isFavorite = userManager.getCurrentUser().getFavoriteUsersUsername().contains(user.getUsername());
        favoriteToggle.setText(isFavorite ? "Remove from Favorites" : "Add to Favorites");

        favoriteToggle.setOnAction(event -> toggleFavorite(favoriteToggle, isFavorite));
        return favoriteToggle;
    }

    private void toggleFavorite(ToggleButton favoriteToggle, boolean isFavorite) {
        if (isFavorite) {
            favoriteToggle.setText("Add to Favorites");
            userManager.getCurrentUser().removeFromFavoriteUsers(user);
        } else {
            favoriteToggle.setText("Remove from Favorites");
            userManager.getCurrentUser().addToFavoriteUsers(user);
        }
    }

    private boolean isCurrentUserProfile() {
        return userManager.getCurrentUser().getUsername().equals(user.getUsername());
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
            user.getPosts().forEach(post -> postsContainer.getChildren().add(createPost("@"+post.getAuthor()+" "+post.getContent())));
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

    private void openEditProfilePage() {
        EditProfilePage editProfilePage = new EditProfilePage(user, primaryStage);
        primaryStage.setScene(editProfilePage.getScene());
    }

    private void navigateBackToHome() {
        HomePage homePage = new HomePage();
        homePage.start(primaryStage);
    }
}
