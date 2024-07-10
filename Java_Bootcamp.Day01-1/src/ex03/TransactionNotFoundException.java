package ex03;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String text) {
        super(text);
    }
}
