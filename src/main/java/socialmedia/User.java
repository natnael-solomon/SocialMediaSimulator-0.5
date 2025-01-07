package socialmedia;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String fullName;
    private String username;
    private Image profilePicture;
    private String bio;
    private String password;
    private final List<User> favoriteUsers;
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
        this.favoriteUsers= new ArrayList<>();
        this.posts = new ArrayList<>();
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
        posts.add(post);
    }


    public List<Post> getPosts() {
        return new ArrayList<>(posts);
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
        return new ArrayList<>(favoriteUsers);
    }

    public void addToFavoriteUsers(User user) {
        this.favoriteUsers.add(user);
    }

    //Simple in-house hash function
    public static String hashPassword(String input) {
        int hash = 7;
        String hashString = "";

        for (int i = 0; i < input.length()-1; i++) {
            hashString = hashString + 31 * hash + input.charAt(i);
        }

        // Combine length of the string with the hash to minimize collisions
        hashString = hashString + hash * 31+ input.length();

        return hashString;
    }

    /*
     * To validate the password by comparing the input password's hash with the stored hashed password *
     */
    public boolean validatePassword(String passwordToBeChecked) {

        return this.password.equals(hashPassword(passwordToBeChecked)); // Compare input hash with stored hash
    }
}
