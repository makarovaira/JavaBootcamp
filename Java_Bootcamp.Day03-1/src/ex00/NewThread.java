package ex00;

public class NewThread extends Thread {
    private final int count;
    private final String message;

    public NewThread(int count, String message) {
        this.count = count;
        this.message = message;
    }

    @Override
    public void run() {
        printText();
    }

    private void printText() {
        for (int i = 0; i < count; i++) {
            System.out.println(this.message);
        }
    }
}
