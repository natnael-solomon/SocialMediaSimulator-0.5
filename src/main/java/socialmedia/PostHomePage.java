package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PostHomePage extends VBox {

    public PostHomePage(Post post, Stage primaryStage) {
        this.setSpacing(10);
        this.getStyleClass().add("post");

        HBox postHeader = createPostHeader(post);
        Label postContent = new Label(post.getContent()); // Set the content
        postContent.getStyleClass().add("post-content");
        postContent.setWrapText(true);

        HBox postActions = createPostActions();

        this.getChildren().addAll(postHeader, postContent, postActions);

        this.setOnMouseClicked(_ -> openPostInNewWindow(post, primaryStage));
    }


    private HBox createPostHeader(Post post) {

        HBox postHeader = new HBox(10);
        VBox userInfo = new VBox(5);

        Label username = new Label(post.getParentUser().getUsername());
        username.getStyleClass().add("post-username");

        Label fullNameLabel = new Label(post.getParentUser().getFullName());
        fullNameLabel.getStyleClass().add("post-full-name");

        userInfo.getChildren().addAll(fullNameLabel, username);

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

    private void openPostInNewWindow(Post post, Stage primaryStage) {
        new PostPage(post, primaryStage);
    }
}
