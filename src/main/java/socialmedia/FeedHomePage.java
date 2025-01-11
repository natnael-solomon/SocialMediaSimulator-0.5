package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FeedHomePage extends ScrollPane {
    private final VBox feed;
    private final Stage primaryStage;

    public FeedHomePage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.feed = createFeedPage();
        this.getStyleClass().add("feed");
        this.setContent(feed);
        this.setFitToWidth(true);
    }

    private VBox createFeedPage() {
        VBox feed = new VBox(25);
        feed.getStyleClass().add("feed");

        String[][] userData = {
                {"Natnael Solomon", "@natnael"},
                {"Nebiyu Samuel", "@nebiyu"},
                {"Leul Teferi", "@chombe"},
                {"Leul Zegeye", "@scarlxrd_x"},
                {"Lealem Addis", "@nowayhome"}
        };

        CreatePostGUI createPostGUI = new CreatePostGUI(this);
        feed.getChildren().add(createPostGUI);

        for (int i = 0; i < userData.length; i++) {
            feed.getChildren().add(new PostHomePage(userData[i], i + 1, "Sample content for post " + (i + 1), primaryStage));
        }
        return feed;
    }

    public void addNewPost(String[] userData, String content) {
        PostHomePage newPost = new PostHomePage(userData, feed.getChildren().size(), content, primaryStage);
        feed.getChildren().add(1, newPost); // Add new post right after the CreatePostGUI
    }
}


class CreatePostGUI extends VBox {
    private final TextField postContent;
    private final FeedHomePage feedHomePage;

    public CreatePostGUI(FeedHomePage feedHomePage) {
        this.feedHomePage = feedHomePage;
        this.getStyleClass().add("create-post-gui");
        this.setSpacing(10);

        postContent = new TextField();
        postContent.setPromptText("What's on your mind?");
        postContent.getStyleClass().add("text-field");

        Button submitButton = new Button("Post");
        submitButton.getStyleClass().add("button");

        submitButton.setOnAction(_ -> createPost());

        this.getChildren().addAll(postContent, submitButton);
    }

    private void createPost() {
        String content = postContent.getText().trim();
        if (!content.isEmpty()) {
            String[] userData = {"New User", "@newuser"}; // Example user data
            feedHomePage.addNewPost(userData, content);
            postContent.clear();
        } else {
            System.out.println("Post content cannot be empty");
        }
    }
}

