package socialmedia;

import static socialmedia.ValidationExceptions.*;

public class Validator {


    public static void validateFields(String username, String password) throws EmptyFieldException, InvalidUsernameException, InvalidPasswordException {

        if (username.isEmpty() || password.isEmpty()) {
            throw new EmptyFieldException("Username and password cannot be empty.");
        }

        // Length check
        if (username.length() < 3 || username.length() > 20) {
            throw new InvalidUsernameException("Username must be between 3 and 20 characters.");
        }
        if (password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters.");
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
    }
}
