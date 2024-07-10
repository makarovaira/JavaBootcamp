package ex02;

import java.util.Arrays;
import java.util.Random;

public class Program {
    private final static String startLen = "--arraySize=";
    private final static String startLen2 = "--threadsCount=";
    private static int arraySize;
    private static int threadsCount;
    private static int[] array;
    private static NewThread[] threads;
    private static DataProcessor dataProcessor;

    public static void main(String[] args) {
        processingInputData(args);
        processFillingArray();
        startThreadsAndCalc();
        System.out.println("Sum by threads: " + dataProcessor.getTotalSum());
    }

    public static void processingInputData(String[] args) {
        if (args.length != 2 || !args[0].startsWith(startLen) || !args[1].startsWith(startLen2)) {
            System.err.println("Incorrect argument");
            System.exit(-1);
        }
        String arraySizeStr = args[0].replace(startLen, "");
        String threadsCountStr = args[1].replace(startLen2, "");

        try {
            arraySize = Integer.parseInt(arraySizeStr);
            threadsCount = Integer.parseInt(threadsCountStr);
            if (arraySize > 2_000_000 || threadsCount < 1 || threadsCount > arraySize) {
                System.err.println("Illegal argument: arraySize or threadsSize");
                System.exit(-1);
            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void processFillingArray() {
        int segment, start;
        int end = 0;
        int index = 0;

        array = new int[arraySize];
        threads = new NewThread[threadsCount];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(2000) - 1;
        }
        dataProcessor = new DataProcessor(array);
        System.out.println("Sum: " + Arrays.stream(array).sum());
        segment = arraySize / threadsCount;

        for (int i = 0; i < threadsCount - 1; i++, index++) {
            start = segment * index;
            end = start + segment - 1;
            threads[i] = new NewThread(dataProcessor, start, end, (i + 1));
        }

        if (arraySize % threadsCount != 0) {
            segment = arraySize - (segment * (threadsCount - 1));
        }

        if (threadsCount > 1) {
            threads[threads.length - 1] = new NewThread(dataProcessor, end + 1, end + segment, threads.length);
        } else {
            threads[0] = new NewThread(dataProcessor, 0, arraySize - 1, 1);
        }
    }

    private static void startThreadsAndCalc() {
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
            for (int j = 0; j < threads.length; j++) {
                threads[j].join();
            }
        } catch (InterruptedException exception) {
            System.err.println(exception.getMessage());
        }
    }

}
