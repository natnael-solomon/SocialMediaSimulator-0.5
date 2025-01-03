
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

        String fullName = ui.getTextFieldText(0);
        String username = ui.getTextFieldText(1);
        String password = ui.getPasswordFieldText(0);
        String confirmPassword = ui.getPasswordFieldText(1);

        // To Validate input
        if (fullName.isEmpty()  || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            ui.showInvalidLoginFeedback("empty field");
        } else if (!password.equals(confirmPassword)) {
            ui.showInvalidLoginFeedback("password");
        }  else if (Main.userList.usernameExists(username)) {
            ui.showInvalidLoginFeedback("username");
        } else {
            currentUser = new User(fullName, username, password);
            Main.userList.addUser(currentUser);
            ui.showCustomDialog("Success", "Account created successfully! Welcome, " + fullName + "!");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
