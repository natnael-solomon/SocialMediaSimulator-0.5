package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PostWindowHomePage {

    public PostWindowHomePage(int postId, String fullName, String username, String content, Stage primaryStage) {
        primaryStage.setTitle("Post Details - Post " + postId);

        VBox postDetails = new VBox(20);
        postDetails.getStyleClass().add("post-window-details");

        // Back button
        Button closeButton = new Button("Back");
        closeButton.getStyleClass().add("post-window-close-button");
        closeButton.setOnAction(event -> primaryStage.close());
        postDetails.getChildren().add(closeButton);

        // Post card
        VBox postCard = new VBox(20);
        postCard.getStyleClass().add("post-window-card");

        HBox userInfo = createPostDetailsHeader(fullName, username);
        Label postContent = new Label(content);
        postContent.getStyleClass().add("post-window-details-content");

        // Comment area and button
        HBox commentSection = new HBox(10);
        commentSection.setAlignment(Pos.CENTER_LEFT);

        TextField commentArea = new TextField();
        commentArea.setPromptText("Add a comment...");
        commentArea.getStyleClass().add("post-window-comment-area");
        commentArea.setMaxWidth(300);

        Button postCommentButton = new Button("Post Comment");
        postCommentButton.getStyleClass().add("post-window-post-button");

        commentSection.getChildren().addAll(commentArea, postCommentButton);

        postCard.getChildren().addAll(userInfo, postContent, commentSection);
        postDetails.getChildren().addAll(postCard, createFloatingCommentSection());

        Scene scene = new Scene(postDetails, 1200, 700);
        scene.getStylesheets().add("file:styles/default2.css");
        primaryStage.setScene(scene);
    }

    private HBox createPostDetailsHeader(String fullName, String username) {
        HBox userInfo = new HBox(15);
        userInfo.getStyleClass().add("post-window-user-info");

        Label userIcon = new Label("👤");
        userIcon.getStyleClass().add("post-window-user-icon");

        Label fullNameLabel = new Label(fullName);
        fullNameLabel.getStyleClass().add("post-window-full-name");

        Label userName = new Label(username);
        userName.getStyleClass().add("post-window-user-name");

        VBox userDetails = new VBox(5);
        userDetails.getChildren().addAll(fullNameLabel, userName);

        userInfo.getChildren().addAll(userIcon, userDetails);
        return userInfo;
    }

    private ScrollPane createFloatingCommentSection() {
        ScrollPane commentSection = new ScrollPane();
        commentSection.getStyleClass().add("post-window-scrollpane");
        commentSection.setFitToWidth(true);
        commentSection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox floatingCommentSection = new VBox(20);
        floatingCommentSection.getStyleClass().add("post-window-scrollpane");

        // Simulating comments with replies
        for (int i = 1; i <= 10; i++) { // Creating 10 top-level comments
            VBox commentBox = new VBox(10);
            Label commentLabel = new Label("User" + i + ": This is a comment " + i);
            commentLabel.getStyleClass().add("post-window-floating-comment");

            // Add the reply input section directly below each comment
            HBox replyInputSection = new HBox(10);
            replyInputSection.setAlignment(Pos.CENTER_LEFT);

            TextField replyTextField = new TextField();
            replyTextField.setPromptText("Write a reply...");
            replyTextField.getStyleClass().add("post-window-reply-textfield");

            Button postReplyButton = new Button("Post Reply");
            postReplyButton.getStyleClass().add("post-window-post-reply-button");

            replyInputSection.getChildren().addAll(replyTextField, postReplyButton);

            // Adding indented replies (these are separate from the reply input)
            VBox repliesBox = new VBox(5);
            repliesBox.setStyle("-fx-padding-left: 20px;"); // Add indentation for replies

            // Adding existing replies
            for (int j = 1; j <= 2; j++) { // Creating 2 replies per comment
                Label replyLabel = new Label("Reply to User" + i + ": This is a reply " + j);
                replyLabel.getStyleClass().add("post-window-floating-comment-reply");
                repliesBox.getChildren().add(replyLabel);
            }

            // Add the reply input section and the replies box below the comment
            commentBox.getChildren().addAll(commentLabel, replyInputSection, repliesBox);
            floatingCommentSection.getChildren().add(commentBox);
        }

        commentSection.setContent(floatingCommentSection);
        return commentSection;
    }
}

/*

package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class PostWindowHomePage {

    private List<String> comments = new ArrayList<>();
    private List<List<String>> replies = new ArrayList<>();

    public PostWindowHomePage(int postId, String fullName, String username, String content, Stage primaryStage) {
        primaryStage.setTitle("Post Details - Post " + postId);

        VBox postDetails = new VBox(20);
        postDetails.getStyleClass().add("post-window-details");

        // Back button
        Button closeButton = new Button("Back");
        closeButton.getStyleClass().add("post-window-close-button");
        closeButton.setOnAction(event -> primaryStage.close());
        postDetails.getChildren().add(closeButton);

        // Post card
        VBox postCard = new VBox(20);
        postCard.getStyleClass().add("post-window-card");

        HBox userInfo = createPostDetailsHeader(fullName, username);
        Label postContent = new Label(content);
        postContent.getStyleClass().add("post-window-details-content");

        // Comment area and button
        HBox commentSection = new HBox(10);
        commentSection.setAlignment(Pos.CENTER_LEFT);

        TextField commentArea = new TextField();
        commentArea.setPromptText("Add a comment...");
        commentArea.getStyleClass().add("post-window-comment-area");
        commentArea.setMaxWidth(300);

        Button postCommentButton = new Button("Post Comment");
        postCommentButton.getStyleClass().add("post-window-post-button");

        postCommentButton.setOnAction(event -> {
            String commentText = commentArea.getText();
            if (!commentText.isEmpty()) {
                comments.add(commentText);  // Add comment to the list
                replies.add(new ArrayList<>());  // Initialize an empty list for replies for this comment
                commentArea.clear();  // Clear the comment text field
                updateFloatingCommentSection(postDetails);  // Update the comment section
            }
        });

        commentSection.getChildren().addAll(commentArea, postCommentButton);

        postCard.getChildren().addAll(userInfo, postContent, commentSection);
        postDetails.getChildren().addAll(postCard, createFloatingCommentSection());

        Scene scene = new Scene(postDetails, 1200, 700);
        scene.getStylesheets().add("file:styles/default2.css");
        primaryStage.setScene(scene);
    }

    private HBox createPostDetailsHeader(String fullName, String username) {
        HBox userInfo = new HBox(15);
        userInfo.getStyleClass().add("post-window-user-info");

        Label userIcon = new Label("👤");
        userIcon.getStyleClass().add("post-window-user-icon");

        Label fullNameLabel = new Label(fullName);
        fullNameLabel.getStyleClass().add("post-window-full-name");

        Label userName = new Label(username);
        userName.getStyleClass().add("post-window-user-name");

        VBox userDetails = new VBox(5);
        userDetails.getChildren().addAll(fullNameLabel, userName);

        userInfo.getChildren().addAll(userIcon, userDetails);
        return userInfo;
    }

    private void updateFloatingCommentSection(VBox floatingCommentSection) {
        // Clear the current content of the floating comment section
        floatingCommentSection.getChildren().clear();

        // Rebuild the comment section with the updated comments and replies
        floatingCommentSection.getChildren().addAll(createFloatingCommentSection().getContent());
    }


    private ScrollPane createFloatingCommentSection() {
        ScrollPane commentSection = new ScrollPane();
        commentSection.getStyleClass().add("post-window-scrollpane");
        commentSection.setFitToWidth(true);
        commentSection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox floatingCommentSection = new VBox(20);
        floatingCommentSection.getStyleClass().add("post-window-scrollpane");

        for (int i = 0; i < comments.size(); i++) {
            String comment = comments.get(i);

            VBox commentBox = new VBox(10);
            Label commentLabel = new Label("User: " + comment);
            commentLabel.getStyleClass().add("post-window-floating-comment");

            // Add the reply input section directly below each comment
            HBox replyInputSection = new HBox(10);
            replyInputSection.setAlignment(Pos.CENTER_LEFT);

            TextField replyTextField = new TextField();
            replyTextField.setPromptText("Write a reply...");
            replyTextField.getStyleClass().add("post-window-reply-textfield");

            Button postReplyButton = new Button("Post Reply");
            postReplyButton.getStyleClass().add("post-window-post-reply-button");

            // Handle Post Reply action
            int finalI = i;
            postReplyButton.setOnAction(event -> {
                String replyText = replyTextField.getText();
                if (!replyText.isEmpty()) {
                    // Add the reply to the corresponding comment's reply list
                    replies.get(finalI).add(replyText);

                    // Clear the reply text field
                    replyTextField.clear();

                    // Refresh the UI with the new reply
                    updateFloatingCommentSection(floatingCommentSection);
                }
            });

            replyInputSection.getChildren().addAll(replyTextField, postReplyButton);

            // Display existing replies for the comment
            VBox repliesBox = new VBox(5);
            repliesBox.setStyle("-fx-padding-left: 20px;");  // Indent replies

            for (String reply : replies.get(i)) {
                Label replyLabel = new Label("Reply: " + reply);
                replyLabel.getStyleClass().add("post-window-floating-comment-reply");
                repliesBox.getChildren().add(replyLabel);
            }

            // Add the reply input section and the replies box below the comment
            commentBox.getChildren().addAll(commentLabel, replyInputSection, repliesBox);
            floatingCommentSection.getChildren().add(commentBox);
        }

        commentSection.setContent(floatingCommentSection);
        return commentSection;
    }
}
*/
