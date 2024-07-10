package ex03;

import java.io.IOException;

public class Program {
    private static String startLen = "--threadsCount=";
    private static int threadCount;
    private static NewThread[] threadsArr;
    private static DownloadManager downloadManager;

    public static void main(String[] args) throws Exception {
        processingInputData(args);
        initializeDownloads();
        startDownloads();

    }

    public static void processingInputData(String[] args) {
        if (args.length != 1 || !args[0].startsWith(startLen)) {
            System.err.println("Incorrect argument");
            System.exit(-1);
        }
        String inputArg = args[0].replace(startLen, "");
        try {
            int count = Integer.parseInt(inputArg);
            if (count < 1) {
                System.err.println("Incorrect count: " + count);
                System.exit(-1);
            }
            threadCount = count;
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void initializeDownloads() throws IOException {
        downloadManager = new DownloadManager();
        threadsArr = new NewThread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threadsArr[i] = new NewThread(i + 1, downloadManager);
        }
    }

    private static void startDownloads() {
        for (int i = 0; i < threadCount; i++) {
            threadsArr[i].start();
        }
    }

}
