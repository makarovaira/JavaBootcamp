package ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DownloadManager {
    private String filePath = "D:\\21schoolProjects\\Java_Bootcamp.Day03-1\\src\\ex03\\files_urls.txt";
    private boolean isAllProcessed = false;
    private Map<Integer, String> downloadUrls = new TreeMap<>();
    private int currentIndex = 1;

    public DownloadManager() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        lines = lines.stream().map(s -> s.trim()).filter(s -> !s.isEmpty()).toList();

        if (lines.isEmpty()) {
            throw new RuntimeException("No URL files found");
        }

        for (String line : lines) {
            String[] parts = line.split("\\s+");
            downloadUrls.put(Integer.parseInt(parts[0]), parts[1]);
        }
    }

    public synchronized boolean isAllProcessed() {
        return isAllProcessed;
    }

    public int findUrlIndex(String url) {
        for (Map.Entry<Integer, String> entry : downloadUrls.entrySet()) {
            if (entry.getValue().equals(url)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public synchronized String getNextUrl() {
        if (!downloadUrls.containsKey(currentIndex)) {
            return null;
        }
        String url = downloadUrls.get(currentIndex++);

        if (!downloadUrls.containsKey(currentIndex)) {
            isAllProcessed = true;
        }
        return url;
    }
}
