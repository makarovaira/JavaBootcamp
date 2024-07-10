package ex02;

public class NewThread extends Thread {
    private int threadNumber;
    private int start;
    private int end;
    private DataProcessor dataProcessor;

    public NewThread(DataProcessor dataProcessor, int start, int end, int threadNumber) {
        this.dataProcessor = dataProcessor;
        this.start = start;
        this.end = end;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        dataProcessor.processData(start, end, threadNumber);
    }
}
