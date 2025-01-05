package socialmedia;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProfilePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create root container
        BorderPane root = new BorderPane();
        root.getStyleClass().add("layout");

        // Header (Modern look with logo)
        HBox header = createHeader();
        root.setTop(header);

        // Profile Content (Profile picture, details)
        VBox profileContent = createProfileContent();
        root.setCenter(profileContent);

        // Sidebar for Posts Feed (Prepopulated)
        VBox sidebar = createTweetFeed();
        root.setRight(sidebar);

        // Footer (Logout button with hover effect)
        HBox footer = createFooter();
        root.setBottom(footer);

        // Set Scene and Stage
        Scene scene = new Scene(root, 1100, 750);
        scene.getStylesheets().add("file:styles/default.css");
        primaryStage.setTitle("Profile Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Header with Logo
    private HBox createHeader() {
        HBox header = new HBox(25);
        header.setAlignment(Pos.CENTER_LEFT);
        header.getStyleClass().add("header");

        Text logo = new Text("Profile");
        logo.setFont(javafx.scene.text.Font.font("Segoe UI", 28));
        logo.setFill(Color.WHITE);

        header.getChildren().add(logo);
        return header;
    }

    // Profile Content (Profile Info)
    private VBox createProfileContent() {
        VBox profileContent = new VBox(40);
        profileContent.setAlignment(Pos.TOP_CENTER);
        profileContent.getStyleClass().add("layout");

        // Profile Info
        VBox profileInfo = createProfileInfo();

        profileContent.getChildren().addAll(profileInfo);
        return profileContent;
    }

    // Profile Information (Profile Image, Name, Bio)
    private VBox createProfileInfo() {
        VBox profileInfo = new VBox(25);
        profileInfo.setAlignment(Pos.CENTER);
        profileInfo.getStyleClass().add("layout");

        // Profile Picture with round shape
        ImageView profileImageView = new ImageView();
        profileImageView.setFitWidth(160);
        profileImageView.setFitHeight(160);
        profileImageView.setPreserveRatio(true);
        profileImageView.getStyleClass().add("profile-picture");

        // Button to change profile picture
        Button changePictureButton = createStyledButton("Change Picture");
        changePictureButton.getStyleClass().add("button-small");
        changePictureButton.setOnAction(e -> openFileChooser(profileImageView));

        // Username and Bio
        Text username = new Text("John Doe");
        username.setFont(javafx.scene.text.Font.font("Segoe UI", 24));
        username.getStyleClass().add("label-first");

        Text bio = new Text("Software Developer | Coffee Lover | Always Learning.");
        bio.setFont(javafx.scene.text.Font.font("Segoe UI", 16));
        bio.getStyleClass().add("bio-label");

        profileInfo.getChildren().addAll(profileImageView, changePictureButton, username, bio);
        return profileInfo;
    }

    // File chooser to update profile picture
    private void openFileChooser(ImageView profileImageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(profileImageView.getScene().getWindow());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(image);
        }
    }

    // Tweets Feed (Prepopulated Posts)
    private VBox createTweetFeed() {
        VBox tweetFeed = new VBox(30);
        tweetFeed.getStyleClass().add("post-container");

        // Prepopulated Tweets (Only showing user's posts)
        VBox tweet1 = createTweet("John Doe", "#excited", "2m ago");
        VBox tweet2 = createTweet("John Doe", "Just finished reading a great book! #booklover", "10m ago");

        tweetFeed.getChildren().addAll(tweet1, tweet2);
        return tweetFeed;
    }

    // Method to create a single Tweet layout
    private VBox createTweet(String username, String content, String timestamp) {
        VBox tweet = new VBox(15);
        tweet.getStyleClass().add("post-container");

        Text userText = new Text(username);
        userText.setFont(javafx.scene.text.Font.font("Segoe UI", 16));
        userText.getStyleClass().add("post-username");

        Text contentText = new Text(content);
        contentText.setFont(javafx.scene.text.Font.font("Segoe UI", 16));
        contentText.getStyleClass().add("post-content");

        Text timeText = new Text(timestamp);
        timeText.setFont(javafx.scene.text.Font.font("Segoe UI", 14));
        timeText.getStyleClass().add("post-timestamp");

        tweet.getChildren().addAll(userText, contentText, timeText);
        return tweet;
    }

    // Footer (Logout button with hover effect)
    private HBox createFooter() {
        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER);
        footer.getStyleClass().add("header");

        Button logoutButton = createStyledButton("Logout");
        logoutButton.getStyleClass().add("button");

        footer.getChildren().add(logoutButton);
        return footer;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("button");
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}