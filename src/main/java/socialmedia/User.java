package socialmedia;

import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;


public class User {

    private String fullName;
    private String username;
    private Image profilePicture;
    private String bio;
    private String password;
    private final List<User> favoriteUsers;
    private final List<String> favoriteUsersUsername;
    private final List<Post> posts;


    public User(String fullName, String username, String password) {
        this(fullName, username, password, null, null);
    }

    public User(String fullName, String username, String password, Image profilePicture, String bio) {
        this.fullName = fullName;
        this.username = username;
        this.password = hashPassword(password);
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.favoriteUsers= new LinkedList<>();
        this.favoriteUsersUsername = new LinkedList<>();
        this.posts = new LinkedList<>();
    }

    public User(String fullName, String username, String password, Image profilePicture) {
        this(fullName, username, password, profilePicture, null);
    }

    public User(String fullName, String username, String password, String bio) {
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

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
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


    public Post getPost(int index) {
        if (index >= 0 && index < posts.size()) {
            return posts.get(index);
        } else {
            return null;  // Return null if the index is invalid
        }
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public List<User> getFavoriteUsers() {
        return new LinkedList<>(favoriteUsers);
    }


    public List<String> getFavoriteUsersUsername(){
        return new LinkedList<>(favoriteUsersUsername);
    }

    public void addToFavoriteUsers(User user) {
        this.favoriteUsers.addFirst(user);
        this.favoriteUsersUsername.addFirst(user.username);
        favoriteUsers.sort((o1, o2) -> o1.getFullName().compareToIgnoreCase(o2.getFullName()));
    }

    public void removeFromFavoriteUsers(User user) {
        favoriteUsers.remove(user);
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
