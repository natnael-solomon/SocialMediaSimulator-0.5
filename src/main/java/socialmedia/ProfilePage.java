package socialmedia;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProfilePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create root container with a modern background color
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f8fafc;");

        // Header (Modern look with logo, search bar, and profile icon)
        HBox header = createHeader();
        root.setTop(header);

        // Profile Content (Profile picture, details, and posts feed)
        HBox profileContent = createProfileContent();
        root.setCenter(profileContent);

        // Footer (Logout button with hover effect)
        HBox footer = createFooter();
        root.setBottom(footer);

        // Set Scene and Stage
        Scene scene = new Scene(root, 1100, 750);
        primaryStage.setTitle("Modern Twitter Profile Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Header with Logo, Search bar, and Profile icon
    private HBox createHeader() {
        HBox header = new HBox(25);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #1DA1F2; -fx-padding: 20px;");

        Text logo = new Text("TEST");
        logo.setFont(Font.font("Segoe UI", 28));
        logo.setFill(Color.WHITE);

        Button searchButton = createStyledButton("Search");
        Button profileButton = createStyledButton("Profile");

        header.getChildren().addAll(logo, searchButton, profileButton);
        return header;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #fff;");
        button.setMinWidth(90);
        button.setMinHeight(40);
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-border-radius: 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #fff;"));
        return button;
    }

    // Profile Content (Profile Info, Posts Feed)
    private HBox createProfileContent() {
        HBox profileContent = new HBox(40);
        profileContent.setAlignment(Pos.TOP_CENTER);
        profileContent.setStyle("-fx-padding: 30px;");

        // Left side: Profile Info
        VBox profileInfo = createProfileInfo();

        // Right side: Tweets Feed (Prepopulated)
        VBox tweetFeed = createTweetFeed();

        profileContent.getChildren().addAll(profileInfo, tweetFeed);
        return profileContent;
    }

    // Profile Information (Profile Image, Name, Bio)
    private VBox createProfileInfo() {
        VBox profileInfo = new VBox(25);
        profileInfo.setAlignment(Pos.CENTER);
        profileInfo.setStyle("-fx-padding: 20px;");

        // Profile Picture with round shape
        ImageView profileImageView = new ImageView();
        profileImageView.setFitWidth(160);  // Ensure image fits within the circle's diameter
        profileImageView.setFitHeight(160); // Maintain proper height proportional to width
        profileImageView.setPreserveRatio(true); // Ensure the image scales proportionally
        profileImageView.setStyle("-fx-background-radius: 80px; -fx-border-radius: 80px; -fx-border-width: 4; -fx-border-color: gray;");  // Make it round using setStyle()

        // Button to change profile picture
        Button changePictureButton = createStyledButton("Change Picture");
        changePictureButton.setStyle("-fx-background-color: #1DA1F2; -fx-text-fill: white; -fx-border-radius: 20px;");
        changePictureButton.setOnAction(e -> openFileChooser(profileImageView));

        // Username and Bio
        Text username = new Text("John Doe");
        username.setFont(Font.font("Segoe UI", 24));
        username.setStyle("-fx-font-weight: bold;");

        Text bio = new Text("Software Developer | Coffee Lover | Always Learning.");
        bio.setFont(Font.font("Segoe UI", 16));
        bio.setFill(Color.GRAY);

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
        tweetFeed.setStyle("-fx-background-color: white; -fx-padding: 25px; -fx-border-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 2);");

        // Prepopulated Tweets (Only showing user's posts)
        VBox tweet1 = createTweet("John Doe", " #excited", "2m ago");
        VBox tweet2 = createTweet("John Doe", "Just finished reading a great book! #booklover", "10m ago");

        tweetFeed.getChildren().addAll(tweet1, tweet2);
        return tweetFeed;
    }

    // Method to create a single Tweet layout
    private VBox createTweet(String username, String content, String timestamp) {
        VBox tweet = new VBox(15);
        tweet.setStyle("-fx-background-color: #f4f6f8; -fx-padding: 20px; -fx-border-radius: 15px;");

        Text userText = new Text(username);
        userText.setFont(Font.font("Segoe UI", 16));
        userText.setStyle("-fx-font-weight: bold;");

        Text contentText = new Text(content);
        contentText.setFont(Font.font("Segoe UI", 16));
        contentText.setFill(Color.BLACK);

        Text timeText = new Text(timestamp);
        timeText.setFont(Font.font("Segoe UI", 14));
        timeText.setFill(Color.GRAY);

        tweet.getChildren().addAll(userText, contentText, timeText);
        return tweet;
    }

    // Footer (Logout button with hover effect)
    private HBox createFooter() {
        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: #1DA1F2; -fx-padding: 20px;");

        Button logoutButton = createStyledButton("Logout");
        logoutButton.setStyle("-fx-background-color: white; -fx-text-fill: #1DA1F2; -fx-border-radius: 25px;");

        footer.getChildren().add(logoutButton);
        return footer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
