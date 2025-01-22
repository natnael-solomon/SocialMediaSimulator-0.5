package socialmedia;

import static socialmedia.ValidationExceptions.*;

public class Validator {


    public static void validateFields(String username, String password) throws EmptyFieldException, InvalidUsernameException, InvalidPasswordException {

        validateNonEmptyFields(username, password);

        // Length check
        if (password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters.");
        }

    }

    public static void validateFields(String fullname, String username, String password, String confirmPassword) throws EmptyFieldException, InvalidUsernameException, InvalidPasswordException, InvalidFullNameException {
        validateNonEmptyFields(fullname, username, password, confirmPassword);
        validateFullName(fullname);
        validateUsername(username);
        validatePassword(password);
        validatePasswordMatch(password, confirmPassword);
    }

    public static void validateNonEmptyFields(String... fields) throws EmptyFieldException {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                throw new EmptyFieldException("All fields are required.");
            }
        }
    }

    public static void validateFullName(String fullname) throws InvalidFullNameException {
        if (!isValidFullName(fullname)) {
            throw new InvalidFullNameException("Full name must contain at least two words, starting with uppercase letters.");
        }
    }

    public static void validatePassword(String password) throws InvalidPasswordException {
        if (password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters.");
        }

        if (!isValidPassword(password)) {
            throw new InvalidPasswordException("Password must contain at least one uppercase letter, digit, and special character.");
        }
    }

    public static void validateUsername(String username) throws InvalidUsernameException {
        if (username.length() < 3 || username.length() > 20) {
            throw new InvalidUsernameException("Username must be between 3 and 20 characters.");
        }

        if (!isValidUsername(username)) {
            throw new InvalidUsernameException("Username can only contain letters, numbers, and underscores.");
        }
    }

    public static void validatePasswordMatch(String password, String confirmPassword) throws InvalidPasswordException {
        if (!password.equals(confirmPassword)) {
            throw new InvalidPasswordException("Passwords do not match.");
        }
    }

    public static boolean isValidFullName(String fullName) {
        String[] words = fullName.trim().split("\\s+");


        // Ensures there are at least two words
        if (words.length < 2) {
            return false;
        }

        // Checks that each word starts with an uppercase letter and is followed by lowercase letters
        for (String word : words) {
            if (word.isEmpty() || !Character.isUpperCase(word.charAt(0)) || !word.substring(1).chars().allMatch(Character::isLowerCase)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidUsername(String username) {
        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*".indexOf(c) >= 0) hasSpecialChar = true;
        }

        return hasUppercase && hasDigit && hasSpecialChar;
    }
}
