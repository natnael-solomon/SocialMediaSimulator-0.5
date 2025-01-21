package socialmedia;

public class ValidationExceptions {

    // Custom exception for invalid username
    public static class InvalidUsernameException extends Exception {
        public InvalidUsernameException(String message) {
            super(message);
        }
    }

    // Custom exception for invalid password
    public static class InvalidPasswordException extends Exception {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    // Custom exception for empty fields
    public static class EmptyFieldException extends Exception {
        public EmptyFieldException(String message) {
            super(message);
        }
    }

    // Custom exception for invalid credentials
    public static class InvalidCredentialsException extends Exception {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }

    public static class InvalidFullNameException extends Exception {
        public InvalidFullNameException(String message) {
            super(message);
        }
    }

    public static class PasswordMismatchException extends Exception {
        public PasswordMismatchException(String message) {
            super(message);
        }
    }

    public static class UsernameAlreadyExistsException extends Exception {
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }
}
