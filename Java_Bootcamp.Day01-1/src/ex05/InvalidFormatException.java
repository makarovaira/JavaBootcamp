package ex05;

public class InvalidFormatException extends RuntimeException {
    public InvalidFormatException(String text) {
        super(text);
    }

    public InvalidFormatException() {
        super();
    }
}
