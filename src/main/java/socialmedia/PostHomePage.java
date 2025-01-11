package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PostHomePage extends VBox {

    public PostHomePage(String[] userData, int postId, String content, Stage primaryStage) {
        this.setSpacing(10);
        this.getStyleClass().add("post");

        HBox postHeader = createPostHeader(userData);
        Label postContent = new Label(content); // Set the content
        postContent.getStyleClass().add("post-content");
        postContent.setWrapText(true);

        HBox postActions = createPostActions();

        this.getChildren().addAll(postHeader, postContent, postActions);

        this.setOnMouseClicked(_ -> openPostInNewWindow(postId, userData[0], userData[1], content, primaryStage));
    }

    private HBox createPostHeader(String[] userData) {
        HBox postHeader = new HBox(10);
        VBox userInfo = new VBox(5);
        userInfo.getChildren().addAll(
                new Label(userData[0]){{getStyleClass().add("post-full-name");}},
                new Label(userData[1]){{getStyleClass().add("post-username");}}
        );
        postHeader.getChildren().addAll(userInfo, new Label("2h ago"){{getStyleClass().add("post-timestamp");}});
        return postHeader;
    }

    private HBox createPostActions() {
        HBox postActions = new HBox(20);
        postActions.getChildren().addAll(
                createActionButton("❤ Like"),
                createActionButton("💬 Comment"),
                createActionButton("🔄 Share")
        );
        return postActions;
    }

    private Button createActionButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("post-action-button");
        button.setOnAction(event -> System.out.println(text + " clicked!")); // Example action
        return button;
    }

    private void openPostInNewWindow(int postId, String fullName, String username, String content, Stage primaryStage) {
        new PostWindowHomePage(postId, fullName, username, content, primaryStage);
    }
}
