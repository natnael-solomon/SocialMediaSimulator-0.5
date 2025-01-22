package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static socialmedia.Main.userManager;

public class PostHomePage extends VBox {


    private final Post post;
    private final Stage primaryStage;

    public PostHomePage(Post post, Stage primaryStage) {
        this.post = post;
        this.primaryStage = primaryStage;
        this.setSpacing(10);
        this.getStyleClass().add("post");

        HBox postHeader = createPostHeader(post);
        Label postContent = new Label(post.getContent()); // Set the content
        postContent.getStyleClass().add("post-content");

        HBox postActions = createPostActions();

        this.getChildren().addAll(postHeader, postContent, postActions);

        this.setOnMouseClicked(_ -> openPostInNewWindow(post, primaryStage));
    }


    private HBox createPostHeader(Post post) {

        HBox postHeader = new HBox(10);
        VBox userInfo = new VBox(5);

        Label username = new Label("@"+ post.getParentUser().getUsername());
        username.getStyleClass().add("post-username");

        Label fullNameLabel = new Label(post.getParentUser().getFullName());
        fullNameLabel.getStyleClass().add("post-full-name");

        userInfo.getChildren().addAll(fullNameLabel, username);

        postHeader.getChildren().addAll(userInfo, new Label(post.getElapsedTime()){{getStyleClass().add("post-timestamp");}});
        return postHeader;
    }

    private HBox createPostActions() {
        HBox postActions = new HBox(20);

        Button likeButton = new Button("👍 "  + post.getLikes() + " Like" + (post.getLikes() > 1 ? "s" : ""));
        likeButton.getStyleClass().add("post-action-button");

        if(post.getLikes()==0){
            likeButton.setText("👍 Like");

        }

        likeButton.setOnAction(event -> {
            boolean liked = post.likePost(userManager.getCurrentUser());
            if (!liked) {
                likeButton.setText("👍 "  + post.getLikes() + " Like" + (post.getLikes() > 1 ? "s" : ""));
            } else {
                likeButton.setText("👍 Already liked - "  + post.getLikes() + " Like" + (post.getLikes() > 1 ? "s" : ""));
            }
        });

        Button commentButton = new Button("💬 " + post.getNumberOfComments() + " Comment" + (post.getNumberOfComments() > 1 ? "s" : ""));

        if(post.getNumberOfComments()==0){
            commentButton.setText("💬 Comment");

        }

        commentButton.getStyleClass().add("post-action-button");
        commentButton.setOnAction(event -> new PostPage(post, primaryStage));

        postActions.getChildren().addAll(
                likeButton,commentButton
        );
        return postActions;
    }


    private void openPostInNewWindow(Post post, Stage primaryStage) {
        new PostPage(post, primaryStage);
    }
}
