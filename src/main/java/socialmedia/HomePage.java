package socialmedia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomePage extends Application {
    private final BorderPane root = new BorderPane();
    private Scene scene = new Scene(root, 1200, 700);

    @Override
    public void start(Stage primaryStage) {
        root.setLeft(new NavigationPanelHomePage());
        root.setTop(new HeaderHomePage());
        root.setCenter(new FeedHomePage(primaryStage));
        root.setRight(new FavoriteHomePage());


        scene.getStylesheets().add("file:styles/default2.css");
        primaryStage.setTitle("Home Page");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void setScene(Scene scene){
        this.scene=scene;
        start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

