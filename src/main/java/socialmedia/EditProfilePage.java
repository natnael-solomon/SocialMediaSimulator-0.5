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
    private final Stage primaryStage;
    private final UiComponent uiComponent;

    public EditProfilePage(User user, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.user = user;
        this.uiComponent = new UiComponent(primaryStage);
        initialize();
    }

    private void initialize() {
        rootLayout = uiComponent.createStackPane("stack-pane");
        VBox content = uiComponent.createVBox("edit-profile-page", 10);

        Label titleLabel = uiComponent.createLabel("Edit Profile", "edit-profile-title", 0.0);

        profilePicture = new ImageView(user.getProfilePicture() != null ? user.getProfilePicture() : new Image("file:icons/profile.jpg", 200, 200, true, true));
        profilePicture.setFitWidth(200);
        profilePicture.setFitHeight(200);
        profilePicture.setClip(new Circle(100, 100, 100));
        profilePicture.getStyleClass().add("edit-profile-picture");

        Button changePictureButton = uiComponent.createButton("Change Profile Picture", "edit-profile-button", this::changeProfilePicture);

        nameField = uiComponent.createTextField("Full Name", "edit-profile-field", 0);
        nameField.setText(user.getFullName());

        usernameField = uiComponent.createTextField("Username", "edit-profile-field", 0);
        usernameField.setText(user.getUsername());

        bioField = uiComponent.createTextField("Bio", "edit-profile-field", 0);
        bioField.setText(user.getBio());

        Button saveButton = uiComponent.createButton("Save Changes", "edit-profile-button", this::saveChanges);

        content.getChildren().addAll(titleLabel, profilePicture, changePictureButton,
                uiComponent.createLabel("Name:", null, 0.0), nameField,
                uiComponent.createLabel("Username:", null, 0.0), usernameField,
                uiComponent.createLabel("Bio:", null, 0.0), bioField, saveButton);

        assert rootLayout != null;
        rootLayout.getChildren().add(content);
    }

    public Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private void changeProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
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
        Stage stage = primaryStage;
        ProfilePage profilePage = new ProfilePage(user, stage);
        stage.setScene(profilePage.getScene());
    }
}