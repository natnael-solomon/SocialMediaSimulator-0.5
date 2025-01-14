package socialmedia;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.io.File;

public class EditProfilePage {
    private final User user;
    private StackPane rootLayout;
    private ImageView profilePicture;
    private TextField nameField;
    private TextField usernameField;
    private TextField bioField;

    public EditProfilePage(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        rootLayout = new StackPane();
        rootLayout.getStyleClass().add("stack-pane");
        VBox content = new VBox(10);

        content.getStyleClass().add("edit-profile-page");

        Label titleLabel = new Label("Edit Profile");
        titleLabel.getStyleClass().add("edit-profile-title");

        profilePicture = new ImageView(user.getProfilePicture() != null ? user.getProfilePicture() : new Image("file:icons/profile.jpg", 200, 200, true, true));
        profilePicture.setFitWidth(200);
        profilePicture.setFitHeight(200);
        profilePicture.setClip(new Circle(100, 100, 100));
        profilePicture.getStyleClass().add("edit-profile-picture");

        Button changePictureButton = new Button("Change Profile Picture");
        changePictureButton.getStyleClass().add("edit-profile-button");
        changePictureButton.setOnAction(event -> changeProfilePicture());

        nameField = new TextField(user.getFullName());
        nameField.setPromptText("Full Name");
        nameField.getStyleClass().add("edit-profile-field");

        usernameField = new TextField(user.getUsername());
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("edit-profile-field");

        bioField = new TextField(user.getBio());
        bioField.setPromptText("Bio");
        bioField.getStyleClass().add("edit-profile-field");

        Button saveButton = new Button("Save Changes");
        saveButton.getStyleClass().add("edit-profile-button");
        saveButton.setOnAction(event -> saveChanges());

        content.getChildren().addAll(titleLabel, profilePicture, changePictureButton, new Label("Name:"), nameField,
                new Label("Username:"), usernameField, new Label("Bio:"), bioField, saveButton);
        rootLayout.getChildren().add(content);
    }

    public Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private void changeProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image newProfilePicture = new Image(selectedFile.toURI().toString(), 400, 400, true, true);

            profilePicture.setImage(newProfilePicture);
            user.setProfilePicture(newProfilePicture);
        }
    }

    private void saveChanges() {
        user.setFullName(nameField.getText());
        user.setUsername(usernameField.getText());
        user.setBio(bioField.getText());
        //todo: save changes to file or...

        // Navigates back to ProfilePage
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        ProfilePage profilePage = new ProfilePage(user, stage);
        stage.setScene(profilePage.getScene());
    }
}