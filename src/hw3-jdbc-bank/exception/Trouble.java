package exception;

public class Trouble extends Exception {
    public Trouble(String errorMessage) {
        super(errorMessage);
    }
    public Trouble(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}