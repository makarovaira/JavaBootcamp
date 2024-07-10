package ex02;

public class DataProcessor {
    private int[] data;
    private int totalSum;

    public DataProcessor(int[] data) {
        this.data = data;
    }

    public synchronized void processData(int start, int end, int threadNumber) {
        int threadSum = 0;

        for (int i = start; i <= end; i++) {
            threadSum += data[i];
        }
        System.out.println("Thread " + threadNumber + ": from " + start + " to " + end + " sum is " + threadSum);
        totalSum += threadSum;
    }

    public int getTotalSum() {
        return totalSum;
    }
}
