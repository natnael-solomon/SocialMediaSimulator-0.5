
package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PostHomePage extends VBox {

    public PostHomePage(String[] userData, int postId, Stage primaryStage) {
        this.setSpacing(10);
        this.getStyleClass().add("post");

        HBox postHeader = createPostHeader(userData);
        Label postContent = new Label("The big, brown fox jumped over the lazy dog.");
        postContent.getStyleClass().add("post-content");
        postContent.setWrapText(true);

        HBox postActions = createPostActions();

        this.getChildren().addAll(postHeader, postContent, postActions);


        this.setOnMouseClicked(event -> openPostInNewWindow(postId, userData[0], userData[1], postContent.getText(), primaryStage));
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
        return button;
    }

    private void openPostInNewWindow(int postId, String fullName, String username, String content, Stage primaryStage) {
        PostWindowHomePage postWindow = new PostWindowHomePage(postId, fullName, username, content, primaryStage);

    }
}
