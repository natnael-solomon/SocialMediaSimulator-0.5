package socialmedia;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PostWindowHomePage {

    public PostWindowHomePage(int postId, String fullName, String username, String content, Stage primaryStage) {
        primaryStage.setTitle("Post Details - Post " + postId);

        VBox postDetails = new VBox(20);
        postDetails.getStyleClass().add("post-window-details");

        VBox postCard = new VBox(20);
        postCard.getStyleClass().add("post-window-card");

        Label space = new Label(" ");

        HBox userInfo = createPostDetailsHeader(fullName, username);
        Label postContent = new Label(content);
        postContent.getStyleClass().add("post-window-details-content");


        TextField commentArea = new TextField();
        commentArea.setPromptText("Add a comment...");
        commentArea.getStyleClass().add("post-window-comment-area");
        commentArea.setMaxWidth(300);


        Button postCommentButton = new Button("Post Comment");
        postCommentButton.getStyleClass().add("post-window-post-button");

       /* // Close button
        Button closeButton = new Button("Back");
        closeButton.getStyleClass().add("post-window-close-button");
        closeButton.setOnAction(event -> primaryStage.show());*/


        postCard.getChildren().addAll(userInfo, postContent,space, commentArea, postCommentButton);
        postDetails.getChildren().add(postCard);


        VBox floatingCommentSection = createFloatingCommentSection();
        postDetails.getChildren().add(floatingCommentSection);
        postDetails.requestFocus();


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


    private VBox createFloatingCommentSection() {
        VBox commentSection = new VBox(10);
        commentSection.getStyleClass().add("post-window-comments");


        commentSection.setStyle("-fx-layout-x: 50px; -fx-layout-y: 350px;");


        for (int i = 1; i <= 3; i++) {
            Label commentLabel = new Label("User" + i + ": This is a floating comment " + i);
            commentLabel.getStyleClass().add("post-window-floating-comment");
            commentLabel.setAlignment(Pos.CENTER);
            commentSection.getChildren().add(commentLabel);
        }

        return commentSection;
    }
}
