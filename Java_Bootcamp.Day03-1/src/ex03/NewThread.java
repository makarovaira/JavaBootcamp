package ex03;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class NewThread extends Thread {
    private int threadNumber;
    private DownloadManager downloadManager;

    public NewThread(int threadNumber, DownloadManager downloadManager) {
        this.threadNumber = threadNumber;
        this.downloadManager = downloadManager;
    }

    @Override
    public void run() {
        while (!downloadManager.isAllProcessed()) {
            String url = downloadManager.getNextUrl();
            byte[] dataBuffer = new byte[1024];
            int bytesRead, fileIndex;

            if (url != null) {
                File file;
                file = getFile(url);
                try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream());
                     FileOutputStream output = new FileOutputStream((file))) {
                    fileIndex = downloadManager.findUrlIndex(url);
                    System.out.println("Thread-" + threadNumber + " start download file number " + fileIndex);
                    while ((bytesRead = input.read(dataBuffer)) != -1) {
                        output.write(dataBuffer, 0, bytesRead);
                    }
                    System.out.println("Thread-" + threadNumber + " finish download file number " + fileIndex);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private File getFile(String url) {
        File file;
        String[] parts = url.split("/");

        file = new File(parts[parts.length - 1]);
        return file;
    }
}
