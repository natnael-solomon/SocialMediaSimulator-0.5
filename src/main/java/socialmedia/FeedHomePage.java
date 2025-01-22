package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static socialmedia.Main.userManager;

public class FeedHomePage extends ScrollPane {
    private Label noPostsLabel;
    private final VBox feed;
    private final Stage primaryStage;


    public FeedHomePage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.feed = createFeedPage();
        this.getStyleClass().add("feed");
        this.setContent(feed);
        this.setFitToWidth(true);
        this.setFitToHeight(true);
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
    }

    private VBox createFeedPage() {
        VBox feed = new VBox(10);
        feed.getStyleClass().add("feed");

        CreatePostGUI createPostGUI = new CreatePostGUI(this);
        feed.getChildren().add(createPostGUI);

        noPostsLabel = new Label("-No Posts Yet.");
        noPostsLabel.getStyleClass().add("favorite-user");

        if(userManager.getPostsFromFavorites().isEmpty()){
            feed.getChildren().add(noPostsLabel);
            feed.setAlignment(Pos.TOP_CENTER);
        }

        // Includes only posts from favorite users by default (and current user's posts ofc).
        for (Post post : userManager.getPostsFromFavorites()) {
            feed.getChildren().add(new PostHomePage(post, primaryStage));
        }

        return feed;
    }

    public void addNewPost(Post post) {
        noPostsLabel.setVisible(false);
        PostHomePage newPost = new PostHomePage(post, primaryStage);
        feed.getChildren().add(1, newPost);
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
            Post post = new Post(content, userManager.getCurrentUser());
            userManager.getCurrentUser().addPost(post);
            feedHomePage.addNewPost(post);
            postContent.clear();
        } else {
            System.out.println("Post content cannot be empty");
        }
    }
}