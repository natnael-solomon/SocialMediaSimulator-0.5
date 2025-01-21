package socialmedia;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import socialmedia.ValidationExceptions.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static socialmedia.Main.userManager;

public class LoginPage extends Application {

    private User currentUser;
    Label errorLabel;

    @Override
    public void start(Stage primaryStage) {
        UiComponent ui = new UiComponent(primaryStage);

        // Set layout
        VBox layout = ui.createVBox("container", 20);

        ImageView appIcon = ui.createImage("file:icons/icon1.png", "icon-medium");
        Label titleLabel = ui.createLabel("Simple Social Media", "label-first", 0.4);
        TextField usernameField = ui.createTextField("Username", "field", 250);
        PasswordField passwordField = ui.createPasswordField("Password", "field", 250);

       errorLabel = ui.createLabel("", "error-label", 0);

        Button loginButton = ui.createButton("Log In", "button", () -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            try {
                // Validate the fields
                Validator.validateFields(username, password);

                // Check if user credentials are valid
                if (userManager.validateLogin(username, password)) {
                    currentUser = userManager.findUserByUsername(username);
                    userManager.setCurrentUser(currentUser);

                    HomePage homePage = new HomePage();
                    homePage.start(primaryStage);
                } else {
                    throw new InvalidCredentialsException("Incorrect username or password.");
                }
            } catch (EmptyFieldException | InvalidUsernameException | InvalidPasswordException | InvalidCredentialsException e) {
                showError(e.getMessage());
                ui.invalidInput("general");
            }
        });


        Label spacingLabel = ui.createLabel("", "", 1);

        Button signUpButton = ui.createButton("Sign Up", "button", () -> {
            SignupPage signUpPage = new SignupPage();
            signUpPage.start(primaryStage);
        });


        Label footerLabel = ui.createLabel("2025 G.C", "footer-login", 1.5);

        // Adds each component to the layout
        layout.getChildren().addAll(appIcon, titleLabel, usernameField, passwordField, loginButton, errorLabel, spacingLabel, signUpButton, footerLabel);


        StackPane rootlayout = ui.createStackPane("layout");

        rootlayout.getChildren().add(layout);
        
        rootlayout.getStyleClass().add("layout");

        Scene scene = new Scene(rootlayout, 1200, 700);
        scene.getStylesheets().add("file:styles/default.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.getStyleClass().remove("hidden");
        errorLabel.getStyleClass().add("visible");

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            errorLabel.getStyleClass().remove("visible");
            errorLabel.getStyleClass().add("hidden");
        });
        pause.play();
    }
}


