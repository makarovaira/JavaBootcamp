package ex05;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String text) {
        super(text);
    }
}
