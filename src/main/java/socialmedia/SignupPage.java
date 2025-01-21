package socialmedia;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static socialmedia.Main.userManager;
import static socialmedia.ValidationExceptions.*;

public class SignupPage extends Application {

    private Label errorLabel;

    @Override
    public void start(Stage primaryStage) {
        UiComponent ui = new UiComponent(primaryStage);

        // Set layout
        VBox layout = ui.createVBox("container", 20);

        Label titleLabel = ui.createLabel("Create a New Account", "label-first", 0.4);
        TextField fullNameField = ui.createTextField("Full Name", "field", 300);
        TextField usernameField = ui.createTextField("Username", "field", 300);
        PasswordField passwordField = ui.createPasswordField("Create Password", "field", 300);
        PasswordField confirmPasswordField = ui.createPasswordField("Confirm Password", "field", 300);

        errorLabel = ui.createLabel("", "hidden", 0);

        Button signUpButton = ui.createButton("Sign Up", "button", () -> {
            handleSignUp(
                    ui,
                    fullNameField.getText().trim(),
                    usernameField.getText().trim(),
                    passwordField.getText().trim(),
                    confirmPasswordField.getText().trim()
            );
        });

        Button backToLoginButton = ui.createButton("Back to Login", "button-small", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.start(primaryStage);
        });

        // Add components to layout
        layout.getChildren().addAll(
                titleLabel,
                fullNameField,
                usernameField,
                passwordField,
                confirmPasswordField,
                errorLabel,
                signUpButton,
                backToLoginButton
        );

        StackPane rootLayout = ui.createStackPane("layout");
        rootLayout.getChildren().add(layout);

        Scene scene = new Scene(rootLayout, 1200, 700);
        scene.getStylesheets().add("file:styles/default.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign Up Page");
        primaryStage.show();
    }

    private void handleSignUp(UiComponent ui, String fullName, String username, String password, String confirmPassword) {
        try {
            // Validates input
            Validator.validateFields(fullName, username, password, confirmPassword);

            if (!password.equals(confirmPassword)) {
                throw new PasswordMismatchException("Passwords do not match.");
            }

            if (userManager.usernameExists(username)) {
                throw new UsernameAlreadyExistsException("Username already exists.");
            }

            // Creates the user object after validation
            User currentUser = new User(fullName, username, password);
            userManager.addUser(currentUser);


        } catch (PasswordMismatchException | UsernameAlreadyExistsException | InvalidFullNameException |
                 InvalidPasswordException | InvalidUsernameException | EmptyFieldException e) {
            showError(e.getMessage());
            ui.invalidInput("general");
        }
    }

    private final PauseTransition pause = new PauseTransition(Duration.seconds(3));

    private void showError(String message) {
        if (!errorLabel.getText().equals(message)) {
            errorLabel.setText(message);
        }

        errorLabel.getStyleClass().remove( "hidden");
        errorLabel.getStyleClass().add("visible");

        pause.setOnFinished(event -> hideError());
        pause.play();
    }

    private void hideError() {
        errorLabel.getStyleClass().remove("visible");
        errorLabel.getStyleClass().add("hidden");
    }
}
