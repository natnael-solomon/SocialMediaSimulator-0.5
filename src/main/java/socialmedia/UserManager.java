package socialmedia;


import java.util.*;

public class UserManager {

    private final List<User> users;
    private User currentUser;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser(){
        if(currentUser!=null){
            return currentUser;
        }
        else {
            return null;
        }
    }

    public boolean addUser(User user) {
        if (!usernameExists(user.getUsername())) {
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeUser(String username) {
        User user = findUserByUsername(username);
        if (user != null) {
            users.remove(user);
            return true;
        } else {
            return false;
        }
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean usernameExists(String username) {
        return findUserByUsername(username) != null;
    }

    public boolean validateLogin(String username, String password) {
        User user = findUserByUsername(username);
        if (user != null && user.validatePassword(password)) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<Post> getAllUsersPosts() {
        List<Post> allPosts = new LinkedList<>();

        for (User user : users) {

            allPosts.addAll(user.getPosts());
        }

        // Sorts posts by timestamp in descending order
        allPosts.sort(Comparator.comparing(Post::getTimestamp).reversed());

        return allPosts;
    }

    public List<Post> getPostsFromFavorites() {

        if (currentUser == null) {
            return new LinkedList<>();
        }

        List<String> favoriteUsersUsername = currentUser.getFavoriteUsersUsername();
        List<Post> allPosts = new LinkedList<>();


        for (String username : favoriteUsersUsername) {
            User favoriteUser = findUserByUsername(username);
            if (favoriteUser != null) {
                allPosts.addAll(favoriteUser.getPosts());
            }
        }

        // Adds current user's posts
        allPosts.addAll(currentUser.getPosts());

        // Sorts posts by timestamp in descending order
        allPosts.sort(Comparator.comparing(Post::getTimestamp).reversed());

        return allPosts;
    }

    public List<User> getUserSuggestions(int threshold) {
        if (currentUser == null) {
            return new ArrayList<>();
        }

        List<User> suggestedUsers = new ArrayList<>();


        // Iterates over all users to find those who share favorite users with the current user
        for (User user : users) {

            if (user == currentUser) {
                continue;
            }

            int commonFavoritesCount = getCommonFavoriteCount(user, currentUser);

            // If the users share a sufficient number of favorite users, add them as a suggestion
            if (commonFavoritesCount >= threshold) {
                suggestedUsers.add(user);
            }
        }

        return suggestedUsers;
    }

    public int getCommonFavoriteCount(User user1, User user2) {
        List<String> favoritesOfUser1 = user1.getFavoriteUsersUsername();
        List<String> favoritesOfUser2 = user2.getFavoriteUsersUsername();

        int commonFavoriteCount = 0;

        for (String favorite : favoritesOfUser1) {
            if (favoritesOfUser2.contains(favorite)) {
                commonFavoriteCount++;
            }
        }

        return commonFavoriteCount;
    }
}

