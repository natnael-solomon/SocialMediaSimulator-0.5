package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private static final UserManager userManager = new UserManager();
    private static final List<User> users = new ArrayList<>();

    // Dummy user data
    private static final String[][] userData = {
            {"Natnael Solomon", "natnael"},
            {"Leul Teferi", "chombe"},
            {"Leul Zegeye", "scarlxrd_x"},
            {"Lealem Addis", "nowayhome"},
            {"Abel Getachew", "sociopath"},
            {"Abenezer Daniel", "Abenixo"},
            {"Zerubabel Takele", "zeritu"},
            {"Nikodimos Hunegn", "nikolas"},
            {"Nebiyu Hailemariam", "arsenal"},
            {"Yosef Tadesse", "yosef"},
            {"Yohannes Afework", "yoniman"},
            {"Yared Feleke", "yarednegu"},
            {"Nebiyu Samuel", "nebiyu"},
    };

    private static final String[] postContents = {
            "Stay positive, and don't forget to take a break every now and then. It’s okay to pause, reflect, and enjoy the present moment.",
            "Just another day to chase dreams. It’s time to make something happen. ",
            "Plans are great, but so is spontaneity \uD83C\uDFB2. Let’s see where the day takes me.",
            "New goals, new challenges, new growth. Here’s to making today better than yesterday! \uD83D\uDE80",
            "Loving this new platform! Big kudos to the devs \uD83D\uDC69\u200D\uD83D\uDCBB\uD83D\uDC68\u200D\uD83D\uDCBB",
            "A quick reminder: You are doing great, even if it doesn’t always feel like it. \uD83D\uDCAA #KeepPushing",
            "This is my first post! \uD83C\uDF05"
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

                    if (!user.getUsername().equals(potentialFavorite.getUsername())) {
                        user.addToFavoriteUsers(potentialFavorite);
                    }
                }
            userManager.addUser(user);
        }

    }

    private static void generatePosts(User user) {
        for (String postContent : postContents) {
            Post post = new Post(postContent, user);

            generateComments(post);

            user.addPost(post);
        }
    }

    private static void generateComments(Post post) {
        for (String commentContent : commentContents) {
            Comment comment = new Comment(commentContent, post, post.getParentUser());

            generateReplies(comment);

            post.addComment(comment);
        }
    }

    private static void generateReplies(Comment comment) {
        for (String replyContent : replyContents) {
            Reply reply = new Reply(replyContent, comment, comment.getParentUser());
            comment.addReply(reply);
        }
    }
}
