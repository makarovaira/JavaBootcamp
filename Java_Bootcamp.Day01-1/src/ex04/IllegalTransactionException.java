package ex04;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String text) {
        super(text);
    }
}
