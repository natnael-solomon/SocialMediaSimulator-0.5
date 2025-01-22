package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    private PasswordField oldPasswordField;
    private PasswordField newPasswordField;
    private PasswordField confirmPasswordField;
    private final Stage primaryStage;
    private final UiComponent uiComponent;
    private Label errorLabel;

    public EditProfilePage(User user, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.user = user;
        this.uiComponent = new UiComponent(primaryStage);
        initialize();
    }

    private void initialize() {
        rootLayout = uiComponent.createStackPane("stack-pane");
        VBox content = uiComponent.createVBox("edit-profile-page", 10);

        setupProfilePicture(content);
        setupPersonalInfo(content);
        setupPasswordChange(content);

        errorLabel = uiComponent.createLabel("","hidden",0);
        content.getChildren().add(errorLabel);

        Button saveButton = uiComponent.createButton("Save Changes", "edit-profile-button", this::saveChanges);
        content.getChildren().add(saveButton);

        assert rootLayout != null;
        rootLayout.getChildren().add(content);
    }

    public Scene getScene() {
        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/profile.css");
        return scene;
    }

    private void setupProfilePicture(VBox content) {
        profilePicture = setUpProfilePicture();
        Button changePictureButton = uiComponent.createButton("Change Profile Picture", "edit-profile-button-small", this::changeProfilePicture);
        content.getChildren().addAll(profilePicture, changePictureButton);
    }

    private ImageView setUpProfilePicture() {
        String profileImagePath = user.getProfilePicturePath();
        Image profileImage = profileImagePath != null ? new Image(profileImagePath) : new Image("file:icons/profile.jpg", 200, 200, true, true);

        ImageView imageView = new ImageView(profileImage);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setClip(new Circle(100, 100, 100));
        imageView.getStyleClass().add("edit-profile-picture");
        return imageView;
    }

    private void setupPersonalInfo(VBox content) {
        double labelWidth = 100;

        HBox nameBox = createLabeledTextField("Full Name:", labelWidth, user.getFullName());
        nameField = (TextField) nameBox.getChildren().get(1);

        HBox usernameBox = createLabeledTextField("Username:", labelWidth, user.getUsername());
        usernameField = (TextField) usernameBox.getChildren().get(1);

        HBox bioBox = createLabeledTextField("Bio:", labelWidth, user.getBio());
        bioField = (TextField) bioBox.getChildren().get(1);

        content.getChildren().addAll(nameBox, usernameBox, bioBox);
        content.setAlignment(Pos.CENTER);
    }

    private HBox createLabeledTextField(String labelText, double labelWidth, String textFieldValue) {
        HBox hbox = uiComponent.createHBox("edit-profile-row", 10);
        Label label = uiComponent.createLabel(labelText, null, 0.0);
        label.setPrefWidth(labelWidth);
        TextField textField = uiComponent.createTextField(labelText, "edit-profile-field", 0);
        textField.setText(textFieldValue);
        hbox.getChildren().addAll(label, textField);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private void setupPasswordChange(VBox content) {
        double labelWidth = 100;

        HBox oldPasswordBox = createLabeledPasswordField("Old Password:", labelWidth);
        oldPasswordField = (PasswordField) oldPasswordBox.getChildren().get(1);

        HBox newPasswordBox = createLabeledPasswordField("New Password:", labelWidth);
        newPasswordField = (PasswordField) newPasswordBox.getChildren().get(1);

        HBox confirmPasswordBox = createLabeledPasswordField("Confirm Password:", labelWidth);
        confirmPasswordField = (PasswordField) confirmPasswordBox.getChildren().get(1);

        Button changePasswordButton = uiComponent.createButton("Change Password", "edit-profile-button-small", this::changePassword);

        content.getChildren().addAll(oldPasswordBox, newPasswordBox, confirmPasswordBox, changePasswordButton);
        content.setAlignment(Pos.CENTER);
    }

    private HBox createLabeledPasswordField(String labelText, double labelWidth) {
        HBox hbox = uiComponent.createHBox("edit-profile-row", 10);
        Label label = uiComponent.createLabel(labelText, null, 0.0);
        label.setPrefWidth(labelWidth);
        PasswordField passwordField = uiComponent.createPasswordField(labelText, "edit-profile-field", 0);
        hbox.getChildren().addAll(label, passwordField);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private void changeProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image newProfilePicture = new Image(selectedFile.toURI().toString(), 400, 400, true, true);
            profilePicture.setImage(newProfilePicture);
            user.setProfilePicturePath(selectedFile.toURI().toString());
        }
    }

    private void changePassword() {
        String oldPassword = oldPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        try {
            // Validates non-empty fields
            Validator.validateNonEmptyFields(oldPassword, newPassword, confirmPassword);

            // Validates old password correctness
            if (!user.validatePassword(oldPassword)) {
                throw new ValidationExceptions.InvalidPasswordException("Current password is incorrect.");
            }

            // Validates new password and confirm password match
            Validator.validatePassword(newPassword);
            Validator.validatePasswordMatch(newPassword, confirmPassword);

            // Updates the user's password
            user.setPassword(newPassword);

            errorLabel.getStyleClass().remove("hidden");
            errorLabel.getStyleClass().add("visible-successful");
            errorLabel.setText("Password changed successfully!");


        } catch (ValidationExceptions.EmptyFieldException | ValidationExceptions.InvalidPasswordException e) {
            uiComponent.invalidInput("password");
            uiComponent.showFeedback(errorLabel, e.getMessage());
        }
    }

    private void saveChanges() {
        user.setFullName(nameField.getText());
        user.setUsername(usernameField.getText());
        user.setBio(bioField.getText());

        Stage stage = primaryStage;
        ProfilePage profilePage = new ProfilePage(user, stage);
        stage.setScene(profilePage.getScene());
    }
}