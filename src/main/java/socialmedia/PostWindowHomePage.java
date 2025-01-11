package socialmedia;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PostWindowHomePage {

    private final List<String> comments = new ArrayList<>();
    private final List<List<String>> replies = new ArrayList<>();
    private final VBox floatingCommentSection = new VBox();

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
        TextField commentArea = ui.createTextField("Add a comment...", "post-window-comment-area", 300);

        commentSection.getChildren().addAll(commentArea, ui.createButton("Post Comment", "post-window-post-button", () -> {
            String commentText = commentArea.getText();
            if (!commentText.isEmpty()) {

                if (comments.isEmpty()) {
                    postDetails.getChildren().add(createFloatingCommentSection(ui));
                }

                comments.addFirst(commentText); // Add new comment to the top
                replies.addFirst(new ArrayList<>()); // Add corresponding reply list
                commentArea.clear(); // Clear the comment area text field
                updateFloatingCommentSection(ui); // Update the UI
            }
        }));

        postCard.getChildren().addAll(userInfo, postContent, commentSection);
        postDetails.getChildren().add(postCard);

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
        commentSection.getStyleClass().add("post-window-background");
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

            VBox commentBox = ui.createVBox("post-window-background", 10);
            Label commentLabel = ui.createLabel("User: " + comment, "post-window-floating-comment", 0);
            commentLabel.setWrapText(true);

            HBox replyInputSection = ui.createHBox("post-window-reply-section", 10);

            TextField replyTextField = ui.createTextField("Write a reply...", "post-window-reply-textfield", 300);
            int finalI = i;
            Button postReplyButton = ui.createButton("Post Reply", "post-window-post-reply-button", () -> {
                String replyText = replyTextField.getText();
                if (!replyText.isEmpty()) {
                    replies.get(finalI).add(replyText); // Add reply to the corresponding comment's reply list
                    replyTextField.clear(); // Clear the reply text field
                    updateFloatingCommentSection(ui); // Refresh the UI
                }
            });

            replyInputSection.getChildren().addAll(replyTextField, postReplyButton);

            VBox repliesBox = ui.createVBox("post-window-replies", 10);
            for (String reply : replies.get(i)) {
                Label replyLabel = ui.createLabel("Reply: " + reply, "post-window-floating-comment-reply", 0);
                replyLabel.setWrapText(true);
                repliesBox.getChildren().add(replyLabel);
            }

            commentBox.getChildren().addAll(commentLabel, replyInputSection, repliesBox);
            floatingCommentSection.getChildren().add(commentBox);
        }
    }
}
