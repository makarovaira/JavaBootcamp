package ex04;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String text) {
        super(text);
    }
}
