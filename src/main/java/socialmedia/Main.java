package socialmedia;

import javafx.application.Application;
import javafx.stage.Stage;

// GROUP MEMBERS
// Leul Teferi - 817/15
// Leul Zegeye - 815/15
// Nebiyu Samuel - 1076/15
// Natnael Solomon - 1062/15

public class Main extends Application {

    public static UserManager userManager = DataGenerator.generateData();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginPage LoginPage = new LoginPage();
        LoginPage.start(stage);
    }
}

