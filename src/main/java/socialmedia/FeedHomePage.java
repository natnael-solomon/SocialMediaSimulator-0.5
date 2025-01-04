package socialmedia;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FeedHomePage extends ScrollPane {

    public FeedHomePage(Stage primaryStage) {
        VBox feed = createFeedPage(primaryStage);
        this.getStyleClass().add("feed");
        this.setContent(feed);
        this.setFitToWidth(true);

    }

    private VBox createFeedPage(Stage primaryStage) {
        VBox feed = new VBox(25);
        feed.getStyleClass().add("feed");

        String[][] userData = {
                {"Natnael Solomon", "@natnael"},
                {"Nebiyu Samuel", "@nebiyu"},
                {"Leul Teferi", "@chombe"},
                {"Leul Zegeye", "@scarlxrd_x"},
                {"Lealem Addis", "@nowayhome"},
                {"Natnael Solomon", "@natnael"},
                {"Nebiyu Samuel", "@nebiyu"},
                {"Leul Teferi", "@chombe"},
                {"Leul Zegeye", "@scarlxrd_x"},
                {"Lealem Addis", "@nowayhome"},
                {"Natnael Solomon", "@natnael"},
                {"Nebiyu Samuel", "@nebiyu"},
                {"Leul Teferi", "@chombe"},
                {"Leul Zegeye", "@scarlxrd_x"},
                {"Lealem Addis", "@nowayhome"},
        };

        for (int i = 0; i < userData.length; i++) {
            feed.getChildren().add(new PostHomePage(userData[i], i + 1, primaryStage));
        }
        return feed;
    }
}
