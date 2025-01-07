package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private static final UserManager userManager = new UserManager();
    private static final List<User> users = new ArrayList<>();

    // Dummy user data
    private static final String[][] userData = {
            {"Natnael Solomon", "natnael"},
            {"Nebiyu Samuel", "nebiyu"},
            {"Leul Teferi", "chombe"},
            {"Leul Zegeye", "scarlxrd_x"},
            {"Lealem Addis", "nowayhome"},
    };

    private static final String[] postContents = {
            "This is my first post!",
            "Loving this new platform!"
    };

    private static final String[] commentContents = {
            "Me too!",
            "We were waiting for you, brother.",
            "A man of culture! Good seeing you here.",
            "Very inspiring post!"
    };

    private static final String[] replyContents = {
            "Thanks a lot!",
            "I appreciate it!",
            "Haha"
    };

    /**
     * Populates the `UserManager` with dummy data and
     *
     * returns the populated `UserManager` instance
     */
    public static UserManager generateData() {
        generateUsers();
        return userManager;
    }

    private static void generateUsers() {
        for (String[] userDatum : userData) {
            String fullName = userDatum[0];
            String username = userDatum[1];
            String password = "password";
            String bio = "This is a bio";

            User user = new User(fullName, username, password, bio);

            generatePosts(user);
            users.add(user);

        }


        for (User user : users) {

            for (User potentialFavorite : users) {
                // Skip adding the user to their own favorites and prevent duplicates
                if (!user.getUsername().equals(potentialFavorite.getUsername()) && user.getFavoriteUsers().size() < 4) {
                    user.addToFavoriteUsers(potentialFavorite);
                }

            }
            userManager.addUser(user);
        }

    }

    private static void generatePosts(User user) {
        for (String postContent : postContents) {
            Post post = new Post(postContent, user.getUsername());

            generateComments(post);

            user.addPost(post);
        }
    }

    private static void generateComments(Post post) {
        for (String commentContent : commentContents) {
            Comment comment = new Comment(commentContent, "Commenter", post);


            generateReplies(comment);


            post.addComment(comment);
        }
    }

    private static void generateReplies(Comment comment) {
        for (String replyContent : replyContents) {
            Reply reply = new Reply(replyContent, "Replier", comment);
            comment.addReply(reply);
        }
    }
}
