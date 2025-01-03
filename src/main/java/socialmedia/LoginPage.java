package socialmedia;

import javafx.application.Application;
import javafx.stage.Stage;

public class LoginPage extends Application {

    private String currentUser;

    @Override
    public void start(Stage primaryStage) {
        UiComponent ui = new UiComponent(primaryStage);

        ui.configureStage("Slick Modern UI Demo");
        ui.loadTheme("file:styles/default.css");

        ui.setLayout( ui.createVBox("layout", 20));


        ui.createImage("file:icons/icon512.png", "icon-medium");
        ui.createLabel("Simple Social Media", "label-first", 0.4);

        ui.createTextField("Username", "field", 300);
        ui.createPasswordField("Password", "field", 300);

        ui.createButton("Log In", "button", () -> {
            String username = ui.getTextFieldText(0);
            String password = ui.getPasswordFieldText(0);
            if (username.isEmpty() || password.isEmpty()) {
                ui.showCustomDialog("Error", "Username and password cannot be empty.");
            } else {
                currentUser = username;
                ui.showCustomDialog("Success", "Welcome, " + username + "!");
            }
        });


        ui.createButton("Forgot Password", "button-small", () ->
                ui.showCustomDialog("Forgot Password", "Password recovery feature coming soon!")
        );

        // Adds spacing :|
        ui.createLabel("", "", 1);


        ui.createButton("Sign Up", "button", () -> {
            SignupPage signUpPage = new SignupPage();
            signUpPage.start(primaryStage);
        });


        ui.createButtonWithIcon("Settings", "button", "file:icons/settings.png", () ->
                ui.showCustomDialog("Settings", "Settings feature coming soon!")
        );

        ui.createLabel("2025 G.C", "footer-login", 1.5);

        ui.displayStage();
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
