
package socialmedia;

import javafx.application.Application;
import javafx.stage.Stage;

public class SignupPage extends Application {

    User currentUser;

    @Override
    public void start(Stage primaryStage) {

        UiComponent ui = new UiComponent(primaryStage);


        ui.configureStage("Sign Up Page");
        ui.loadTheme("file:styles/default.css");

        ui.setLayout( ui.createVBox("layout", 20));

        ui.createLabel("Create a New Account", "label-first", 0.1);

        ui.createTextField("Full Name", "field", 300);
        ui.createTextField("Username", "field", 300);
        ui.createPasswordField("Create Password", "field", 300);
        ui.createPasswordField("Confirm Password", "field", 300);


        ui.createButton("Sign Up", "button", () -> handleSignUp(ui));
        ui.createButton("Back to Login", "button-small", () -> new LoginPage().start(primaryStage));

        ui.displayStage();
    }

    private void handleSignUp(UiComponent ui) {
        try {
            String fullName = ui.getTextFieldText(0);
            String username = ui.getTextFieldText(1);
            String password = ui.getPasswordFieldText(0);
            String confirmPassword = ui.getPasswordFieldText(1);

            // Validate input
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                ui.showInvalidLoginFeedback("empty field");
                return;
            }

            if (!password.equals(confirmPassword)) {
                ui.showInvalidLoginFeedback("password");
                ui.showCustomDialog("Error", "Passwords do not match.");
                return;
            }

            if (Main.userManager.usernameExists(username)) {
                ui.showInvalidLoginFeedback("username");
                ui.showCustomDialog("Error", "Username already exists.");
                return;
            }

            currentUser = new User(fullName, username, password);
            Main.userManager.addUser(currentUser);
            ui.showCustomDialog("Success", "Account created successfully! Welcome, " + fullName + "!");

        } catch (Exception e) {
            ui.showCustomDialog("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
}
}
