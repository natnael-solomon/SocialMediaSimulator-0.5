package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PostWindowHomePage {

    private final List<String> comments = new ArrayList<>();
    private final List<List<String>> replies = new ArrayList<>();
    private final VBox floatingCommentSection;

    public PostWindowHomePage(int postId, String fullName, String username, String content, Stage primaryStage) {
        UiComponent ui = new UiComponent(primaryStage);
        ui.configureStage("Post Details - Post " + postId);
        ui.loadTheme("file:styles/default2.css");

        VBox postDetails = ui.createVBox("post-window-details", 20);
        ui.setLayout(postDetails);

        // Back button
        ui.createButton("Back", "post-window-close-button", () -> new HomePage().start(primaryStage));

        // Post card
        VBox postCard = ui.createVBox("post-window-card", 20);
        HBox userInfo = createPostDetailsHeader(ui, fullName, username);
        Label postContent = ui.createLabel(content, "post-window-details-content", 0);

        HBox commentSection = ui.createHBox("post-window-comment-section", 10);
        commentSection.setAlignment(Pos.CENTER_LEFT);
        TextField commentArea = ui.createTextField("Add a comment...", "post-window-comment-area", 300);

        commentSection.getChildren().addAll(commentArea, ui.createButton("Post Comment", "post-window-post-button", () -> {
            String commentText = commentArea.getText();
            if (!commentText.isEmpty()) {
                comments.add(commentText);  // Add comment to the list
                replies.add(new ArrayList<>());  // Initialize an empty list for replies for this comment
                commentArea.clear();  // Clear the comment text field
                updateFloatingCommentSection(ui);  // Update the comment section
            }
        }));

        postCard.getChildren().addAll(userInfo, postContent, commentSection);
        floatingCommentSection = ui.createVBox("post-window-scrollpane", 20);
        postDetails.getChildren().addAll(postCard, createFloatingCommentSection(ui));

        ui.displayStage();
    }

    private HBox createPostDetailsHeader(UiComponent ui, String fullName, String username) {
        HBox userInfo = ui.createHBox("post-window-user-info", 15);
        Label userIcon = ui.createLabel("👤", "post-window-user-icon", 0);
        Label fullNameLabel = ui.createLabel(fullName, "post-window-full-name", 0);
        Label userName = ui.createLabel(username, "post-window-user-name", 0);

        VBox userDetails = ui.createVBox("user-details", 5);
        userDetails.getChildren().addAll(fullNameLabel, userName);
        userInfo.getChildren().addAll(userIcon, userDetails);

        return userInfo;
    }

    private ScrollPane createFloatingCommentSection(UiComponent ui) {
        ScrollPane commentSection = new ScrollPane();
        commentSection.getStyleClass().add("post-window-scrollpane");
        commentSection.setFitToWidth(true);
        commentSection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        updateFloatingCommentSection(ui);
        commentSection.setContent(floatingCommentSection);
        return commentSection;
    }

    private void updateFloatingCommentSection(UiComponent ui) {
        floatingCommentSection.getChildren().clear();

        for (int i = 0; i < comments.size(); i++) {
            String comment = comments.get(i);

            VBox commentBox = ui.createVBox("comment-box", 10);
            Label commentLabel = ui.createLabel("User: " + comment, "post-window-floating-comment", 0);

            HBox replyInputSection = ui.createHBox("reply-input-section", 10);
            replyInputSection.setAlignment(Pos.CENTER_LEFT);
            TextField replyTextField = ui.createTextField("Write a reply...", "post-window-reply-textfield", 300);
            int finalI = i;
            Button postReplyButton = ui.createButton("Post Reply", "post-window-post-reply-button", () -> {
                String replyText = replyTextField.getText();
                if (!replyText.isEmpty()) {
                    replies.get(finalI).add(replyText);  // Add reply to the corresponding comment's reply list
                    replyTextField.clear();  // Clear the reply text field
                    updateFloatingCommentSection(ui);  // Refresh the UI with the new reply
                }
            });

            replyInputSection.getChildren().addAll(replyTextField, postReplyButton);

            VBox repliesBox = ui.createVBox("replies-box", 5);
            repliesBox.setStyle("-fx-padding-left: 20px;");  // Indent replies

            for (String reply : replies.get(i)) {
                Label replyLabel = ui.createLabel("Reply: " + reply, "post-window-floating-comment-reply", 0);
                repliesBox.getChildren().add(replyLabel);
            }

            commentBox.getChildren().addAll(commentLabel, replyInputSection, repliesBox);
            floatingCommentSection.getChildren().add(commentBox);
        }
    }
}











/*
package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PostWindowHomePage {

    public PostWindowHomePage(int postId, String fullName, String username, String content, Stage primaryStage) {
        UiComponent ui = new UiComponent(primaryStage);
        ui.configureStage("Post Details - Post " + postId);
        ui.loadTheme("file:styles/default2.css");

        VBox postDetails = ui.createVBox("post-window-details", 20);
        ui.setLayout(postDetails);


        ui.createButton("Back", "post-window-close-button", () -> new HomePage().start(primaryStage));


        VBox postCard = ui.createVBox("post-window-card", 20);
        HBox userInfo = createPostDetailsHeader(ui, fullName, username);
        Label postContent = ui.createLabel(content, "post-window-details-content", 0);

        HBox commentSection = ui.createHBox("post-window-comment-section", 10);
        commentSection.setAlignment(Pos.CENTER_LEFT);
        TextField commentArea = ui.createTextField("Add a comment...", "post-window-comment-area", 300);


        commentSection.getChildren().addAll(commentArea, ui.createButton("Post Comment", "post-window-post-button", () -> {
            // Handle post comment action
        }));

        postCard.getChildren().addAll(userInfo, postContent, commentSection);
        postDetails.getChildren().addAll(postCard, createFloatingCommentSection(ui));

        ui.displayStage();
    }

    private HBox createPostDetailsHeader(UiComponent ui, String fullName, String username) {
        HBox userInfo = ui.createHBox("post-window-user-info", 15);
        Label userIcon = ui.createLabel("👤", "post-window-user-icon", 0);
        Label fullNameLabel = ui.createLabel(fullName, "post-window-full-name", 0);
        Label userName = ui.createLabel(username, "post-window-user-name", 0);

        VBox userDetails = ui.createVBox("user-details", 5);
        userDetails.getChildren().addAll(fullNameLabel, userName);
        userInfo.getChildren().addAll(userIcon, userDetails);

        return userInfo;
    }

    private ScrollPane createFloatingCommentSection(UiComponent ui) {
        ScrollPane commentSection = new ScrollPane();
        commentSection.getStyleClass().add("post-window-scrollpane");
        commentSection.setFitToWidth(true);
        commentSection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox floatingCommentSection = ui.createVBox("post-window-scrollpane", 20);

        // Simulating comments with replies
        for (int i = 1; i <= 10; i++) { // Creating 10 top-level comments
            VBox commentBox = ui.createVBox("comment-box", 10);
            Label commentLabel = ui.createLabel("User" + i + ": This is a comment " + i, "post-window-floating-comment", 0);

            // Add the reply input section directly below each comment
            HBox replyInputSection = ui.createHBox("reply-input-section", 10);
            replyInputSection.setAlignment(Pos.CENTER_LEFT);
            TextField replyTextField = ui.createTextField("Write a reply...", "post-window-reply-textfield", 300);
            Button postReplyButton = ui.createButton("Post Reply", "post-window-post-reply-button", () -> {
                // Handle post reply action
            });

            replyInputSection.getChildren().addAll(replyTextField, postReplyButton);

            // Adding indented replies (these are separate from the reply input)
            VBox repliesBox = ui.createVBox("replies-box", 5);
            repliesBox.setStyle("-fx-padding-left: 20px;"); // Add indentation for replies

            // Adding existing replies
            for (int j = 1; j <= 2; j++) { // Creating 2 replies per comment
                Label replyLabel = ui.createLabel("Reply to User" + i + ": This is a reply " + j, "post-window-floating-comment-reply", 0);
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



