package ch.tofind.reflexia.errors;

public class LobbyClosed extends Exception {

    public LobbyClosed() {

    }

    public LobbyClosed(String message) {
        super(message);
    }
}
