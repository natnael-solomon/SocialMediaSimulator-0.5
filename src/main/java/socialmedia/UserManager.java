package socialmedia;


import java.util.ArrayList;
import java.util.List;

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

    public User getUser(int index) {
        if (index >= 0 && index < users.size()) {
            return users.get(index);
        } else {
            return null;
        }
    }
}