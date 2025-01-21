package socialmedia;

import static socialmedia.ValidationExceptions.*;

public class Validator {


    public static void validateFields(String username, String password) throws EmptyFieldException, InvalidUsernameException, InvalidPasswordException {

        if (username.isEmpty() || password.isEmpty()) {
            throw new EmptyFieldException("Username and password cannot be empty.");
        }

        // Length check
        if (password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters.");
        }

    }


    public static void validateFields(String fullname, String username, String password, String confirmPassword) throws EmptyFieldException, InvalidUsernameException, InvalidPasswordException, InvalidFullNameException {

        if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            throw new EmptyFieldException("All fields are required.");
        }

        if (!isValidFullName(fullname)) {
            throw new InvalidFullNameException("Full name must contain at least two words, starting with uppercase letters.");
        }

        // Length check
        if (username.length() < 3 || username.length() > 20) {
            throw new InvalidUsernameException("Username must be between 3 and 20 characters.");
        }

        // (alphanumeric and underscore)
        boolean isValidUsername = true;
        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                isValidUsername = false;
                break;
            }
        }
        if (!isValidUsername) {
            throw new InvalidUsernameException("Username can only contain letters, numbers, and underscores.");
        }

        if (password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters.");
        }

        //(at least one uppercase letter, one digit, and one special character)
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            if (Character.isDigit(c)) hasDigit = true;
            if ("!@#$%^&*".indexOf(c) >= 0) hasSpecialChar = true;
        }

        if (!hasUppercase || !hasDigit || !hasSpecialChar) {
            throw new InvalidPasswordException("Password must contain at least one uppercase letter, digit, and special character.");
        }

        if (!password.equals(confirmPassword)) {
            throw new InvalidPasswordException("Passwords do not match.");
        }
    }

    private static boolean isValidFullName(String fullName) {
        // Splits the full name into words
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

}
