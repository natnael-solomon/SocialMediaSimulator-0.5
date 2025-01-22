package socialmedia;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class User {

    private String fullName;
    private String username;
    private String profilePicturePath;
    private String bio;
    private String password;
    private final Set<Integer> likedPostIds;
    private final List<String> favoriteUsersUsername;
    private final List<Post> posts;


    public User(String fullName, String username, String password, String profilePicturePath, String bio) {
        this.fullName = fullName;
        this.username = username;
        this.password = hashPassword(password);
        this.profilePicturePath = profilePicturePath;
        this.bio = bio;
        this.favoriteUsersUsername = new LinkedList<>();
        this.posts = new LinkedList<>();
        this.likedPostIds = new HashSet<>();
    }

    public User(String fullName, String username, String password) {
        this(fullName, username, password, null, null);
    }

    public User(String fullName, String username, String password, String profilePicturePath) {
        this(fullName, username, password, profilePicturePath, null);
    }

    public User(String fullName, String username, String password, String bio, boolean ignoredIsProfilePictureNull) {
        this(fullName, username, password, null, bio);
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public void addPost(Post post) {
        posts.addFirst(post);
    }


    public List<Post> getPosts() {
        return new LinkedList<>(posts);
    }


    public void removePost(Post post) {
        posts.remove(post);
    }

    public Set<Integer> getLikedPostIds() {
        return new HashSet<>(likedPostIds);
    }

    // adds the post ID to likedPostIds when a user likes a post
    public void addLikedPostId(int postId) {
        likedPostIds.add(postId);
    }


    public boolean likePost(Post post) {
        return post.likePost(this);
    }


    public List<String> getFavoriteUsersUsername(){
        return new LinkedList<>(favoriteUsersUsername);
    }

    public void addToFavoriteUsers(User user) {
        favoriteUsersUsername.addFirst(user.getUsername());
    }


    public void removeFromFavoriteUsers(User user) {
        favoriteUsersUsername.remove(user.getUsername());
    }

    //Simple in-house hash function
    public static String hashPassword(String input) {
        int hash = 7;
        StringBuilder hashString = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            hash = hash * 31 * input.charAt(i);

            // Map to printable characters
            char newChar = (char) ('A' + Math.abs(hash % 62));


            hashString.append(newChar);
        }

        hashString.append((char) ('A' + Math.abs(hash * input.length() % 62)));
        hashString.insert(Math.abs(hash*31)%hashString.length(),Math.abs(hash));

        return hashString.toString();
    }

    /*
     * To validate the password by comparing the input password's hash with the stored hashed password *
     */
    public boolean validatePassword(String passwordToBeChecked) {

        return this.password.equals(hashPassword(passwordToBeChecked)); // Compare input hash with stored hash
    }
}
