package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static socialmedia.Main.userManager;

public class PostPage {

    private final VBox floatingCommentSection = new VBox();
    private final Post post;
    private final Stage primaryStage;

    public PostPage(Post post, Stage primaryStage) {
        this.post = post;
        this.primaryStage = primaryStage;

        UiComponent ui = new UiComponent(primaryStage);
        ui.configureStage("Post Details - Post ");
        ui.loadTheme("file:styles/default2.css");

        VBox postDetails = ui.createVBox("post-window-details", 20);
        ui.setLayout(postDetails);

        // Back button
        ui.createButton("Back", "post-window-close-button", () -> new HomePage().start(primaryStage));

        // Post card
        VBox postCard = ui.createVBox("post-window-card", 20);
        HBox userInfo = createPostDetailsHeader(ui, post.getParentUser().getFullName(), "@" + post.getAuthor());
        Label postContent = ui.createLabel(post.getContent(), "post-window-details-content", 0);

        HBox commentSection = ui.createHBox("post-window-comment-box", 10);
        TextField commentArea = ui.createTextField("Add a comment...", "post-window-comment-area", 300);

        commentSection.getChildren().addAll(commentArea, ui.createButton("Post Comment", "post-window-post-button", () -> {
            String commentText = commentArea.getText();
            if (!commentText.isEmpty()) {
                Comment newComment = new Comment(commentText, post, userManager.getCurrentUser());
                commentArea.clear(); // Clears the comment area text field
                updateFloatingCommentSection(ui); // Updates the UI
            }
        }));

        postCard.getChildren().addAll(userInfo, postContent, commentSection);
        postDetails.getChildren().add(postCard);

        if(post.getComments().isEmpty()) { updateFloatingCommentSection(ui); }
        postDetails.getChildren().add(createFloatingCommentSection(ui));


        ui.displayStage();
    }

    private HBox createPostDetailsHeader(UiComponent ui, String fullName, String username) {
        HBox userInfo = ui.createHBox("post-window-user-info", 15);
        Label userIcon = ui.createLabel("👤", "post-window-user-icon", 0);
        Label fullNameLabel = ui.createLabel(fullName, "post-window-full-name", 0);
        Label userName = ui.createLabel(username, "post-window-user-name", 0);
        userName.setOnMouseClicked(event -> {
            ProfilePage profilePage = new ProfilePage(post.getParentUser(), primaryStage);
            Stage stage = new Stage();
            stage.setScene(profilePage.getScene());
            stage.show();
        });

        VBox userDetails = ui.createVBox("user-details", 5);
        userDetails.getChildren().addAll(fullNameLabel, userName);
        userInfo.getChildren().addAll(userIcon, userDetails);

        return userInfo;
    }

    private ScrollPane createFloatingCommentSection(UiComponent ui) {

        ScrollPane commentSection = new ScrollPane();

        if(!post.getComments().isEmpty()) {

            commentSection.getStyleClass().add("post-window-background");
            commentSection.setFitToWidth(true);
            commentSection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            updateFloatingCommentSection(ui);
            commentSection.setContent(floatingCommentSection);
        }else {

            commentSection.getStyleClass().add("post-window-background");
            commentSection.setFitToWidth(true);
            commentSection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            commentSection.setContent(floatingCommentSection);
        }
        return commentSection;
    }

    private void updateFloatingCommentSection(UiComponent ui) {

        if (post.getComments().isEmpty()) {
            Label noCommentLabel = ui.createLabel("- No Comments yet\n\n\n","post-window-floating-comment", 0);
            noCommentLabel.setAlignment(Pos.CENTER);
            floatingCommentSection.getStyleClass().add("post-window-background");
            floatingCommentSection.getChildren().add(noCommentLabel);
        }
        else {

            floatingCommentSection.getChildren().clear();

            for (Comment comment : post.getComments()) {
                VBox commentBox = ui.createVBox("post-window-background", 10);

                Label commentLabel = ui.createLabel("👤- @" + comment.getAuthor() + ": " + comment.getContent(), "post-window-floating-comment", 0);
                commentLabel.setWrapText(true);

                // Reply input section
                HBox replyInputSection = ui.createHBox("post-window-reply-section", 10);
                TextField replyTextField = ui.createTextField("Write a reply...", "post-window-reply-textfield", 300);
                Button postReplyButton = ui.createButton("Post Reply", "post-window-post-reply-button", () -> {
                    String replyText = replyTextField.getText();
                    if (!replyText.isEmpty()) {
                        new Reply(replyText, comment, userManager.getCurrentUser());
                        replyTextField.clear(); // Clear the input field
                        updateFloatingCommentSection(ui); // Refresh the UI
                    }
                });
                replyInputSection.getChildren().addAll(replyTextField, postReplyButton);

                // Replies section
                VBox repliesBox = ui.createVBox("post-window-replies", 10);
                for (Reply reply : comment.getReplies()) {

                    Label replyLabel;
                    if (post.getAuthor().equals(reply.getAuthor())) {
                        replyLabel = ui.createLabel("@" + reply.getAuthor() + ": " + reply.getContent(), "post-window-floating-comment-owner-reply", 0);
                    } else {
                        replyLabel = ui.createLabel("@" + reply.getAuthor() + ": " + reply.getContent(), "post-window-floating-comment-reply", 0);
                    }
                    replyLabel.setWrapText(true);
                    repliesBox.getChildren().add(replyLabel);
                }

                // Adds components to the comment box
                commentBox.getChildren().addAll(commentLabel, replyInputSection, repliesBox);
                floatingCommentSection.getChildren().add(commentBox);
            }
       }
    }
}
