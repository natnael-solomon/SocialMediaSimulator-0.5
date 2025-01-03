/*
package socialmedia;


import javafx.application.Application;
import javafx.stage.Stage;

public class Theme extends Application {

    private String currentUser;

    @Override
    public void start(Stage primaryStage) {
        UiComponent ui = new UiComponent(primaryStage);

        ui.configureStage("UI Demo");

        ui.loadTheme("file:styles/default.css");

        ui.setupLayout("layout");



        ui.addButtonWithIcon("Settings", "button", "file:icons/settings.png",
                () -> ui.showCustomDialog("Settings", "Settings feature coming soon!"));

        ui.addImage("file:icons/logo.png", "image");

        ui.addButton("Toggle Theme", "button", () -> {
            String currentTheme = ui.getScene().getStylesheets().get(0);
            if (currentTheme.contains("default.css")) {
                ui.loadTheme("file:styles/dark.css");
            } else {
                ui.loadTheme("file:styles/default.css");
            }
        });

        ui.displayStage();
    }

    public String getCurrentUser() {
        return currentUser;
    }

}
*/
