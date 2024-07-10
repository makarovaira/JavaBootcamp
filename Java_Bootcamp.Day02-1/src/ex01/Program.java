package ex01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Illegal arguments");
            System.exit(1);
        }

        String firstPath = args[0];
        String secondPath = args[1];
        ArrayList<String> arrayWordsFirstFile = getAllWordsFromFile(firstPath);
        ArrayList<String> arrayWordsSecondFile = getAllWordsFromFile(secondPath);

        if (arrayWordsFirstFile.isEmpty() && arrayWordsSecondFile.isEmpty()) {
            System.out.println("Similarity = 0");
            System.exit(0);
        }

        Map<String, Integer> firstMap = createMapWithCount(arrayWordsFirstFile);
        Map<String, Integer> secondMap = createMapWithCount(arrayWordsSecondFile);
        HashSet<String> dictionary = new HashSet<>();
        dictionary.addAll(firstMap.keySet());
        dictionary.addAll(secondMap.keySet());
        saveDictionaryToFile(dictionary);
        Integer[] firstVector = makeVector(dictionary, firstMap);
        Integer[] secondVector = makeVector(dictionary, secondMap);
        calcResult(firstVector, secondVector);
    }

    private static ArrayList<String> getAllWordsFromFile(String filename) {
        ArrayList<String> wordsList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             Scanner scanner = new Scanner(fileInputStream)) {

            while (scanner.hasNext()) {
                String word = scanner.next();
                wordsList.add(word);
            }
        } catch (Exception e) {
            System.out.println("File not found");
            System.exit(-1);
        }
//        for (String word : wordsList) {
//            System.out.println(word);
//        }
        return wordsList;
    }

    private static Map<String, Integer> createMapWithCount(ArrayList<String> arrayList) {
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : arrayList) {
            if (frequency.containsKey(word)) {
                frequency.put(word, frequency.get(word) + 1);
            } else {
                frequency.put(word, 1);
            }
        }
//        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
        return frequency;
    }

    private static void saveDictionaryToFile(HashSet<String> wordSet) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("dictionary.txt")) {
            for (String word : wordSet) {
                fileOutputStream.write(word.getBytes());
                fileOutputStream.write('\n');
            }
        } catch (IOException exception) {
            System.out.println("Error while saving dictionary to file: " + exception.getMessage());
            System.exit(-1);
        }
    }

    private static Integer[] makeVector(HashSet<String> dictionary, Map<String, Integer> frequency) {
        ArrayList<String> arrayList = new ArrayList<>(dictionary);
        Integer[] vector = new Integer[arrayList.size()];
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (frequency.get(arrayList.get(i)) == null) {
                vector[i] = 0;
            } else {
                vector[i] = frequency.get(arrayList.get(i));
            }
        }
//        for(int i : vector) {
//            System.out.println(i);
//        }
        return vector;
    }

    private static void calcResult(Integer[] firstVector, Integer[] secondVector) {
        int dotProduct = 0;
        double normA = 0;
        double normB = 0;

        for (int i = 0; i < firstVector.length; i++) {
            dotProduct += firstVector[i] * secondVector[i];
            normA += Math.pow(firstVector[i], 2);
            normB += Math.pow(secondVector[i], 2);
        }

        normA = Math.sqrt(normA);
        normB = Math.sqrt(normB);

        double denominator = normA * normB;

        if (denominator != 0) {
            double ans = dotProduct / denominator;
            System.out.println("Similarity = " + String.format("%.3f", ans));
        } else {
            System.out.println("One of the vectors is zero. It's impossible to calculate the cosine similarity");
            System.exit(0);
        }
    }
}
