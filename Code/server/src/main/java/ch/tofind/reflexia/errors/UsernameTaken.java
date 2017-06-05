package ch.tofind.reflexia.errors;

public class UsernameTaken extends Exception {

    public UsernameTaken() {

    }

    public UsernameTaken(String message) {
        super(message);
    }
}
