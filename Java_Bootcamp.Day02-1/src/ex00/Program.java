package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final String FILE_PATH = "D:\\21schoolProjects\\Java_Bootcamp.Day02-1\\src\\ex00\\signature.txt";
    private static final String RESULT_PATH = "D:\\21schoolProjects\\Java_Bootcamp.Day02-1\\src\\ex00\\result.txt";

    public static void main(String[] args) {
        Map<String, int[]> signaturesMap = new HashMap<>();

        try (FileInputStream inputFile = new FileInputStream(FILE_PATH)) {
            Scanner scan = new Scanner(inputFile);
            while (scan.hasNextLine()) {
                String lineArgs = scan.nextLine();
                String[] argsArray = lineArgs.split(",");
                String[] signatureStrBytes = argsArray[1].trim().split(" ");
                int[] signatureBytes = new int[signatureStrBytes.length];
                for (int i = 0; i < signatureStrBytes.length; i++) {
                    signatureBytes[i] = Integer.parseInt(signatureStrBytes[i], 16);
                }
                signaturesMap.put(argsArray[0], signatureBytes);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }

        Scanner scan = new Scanner(System.in);
        String inputFile = scan.nextLine();

        while (!inputFile.equals("42")) {
            try (FileInputStream line = new FileInputStream(inputFile)) {
                byte[] bytes = new byte[8];
                line.read(bytes);
                int[] intArray = new int[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    intArray[i] = bytes[i];
                }
                checkSignature(signaturesMap, intArray);
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
            inputFile = scan.nextLine();
        }
    }

    private static void checkSignature(Map<String, int[]> map, int[] signature) {
        try (FileOutputStream out = new FileOutputStream(RESULT_PATH, true)) {
            for (Map.Entry<String, int[]> entry : map.entrySet()) {
                int[] getV = entry.getValue();

                if (findSubArr(signature, getV)) {
                    out.write((entry.getKey() + "\n").getBytes());
                    System.out.println("PROCESSED");
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public static boolean findSubArr(int[] arr,int[] subarr) {
        int lim = arr.length - subarr.length;
        int[] tmpArr=new int[subarr.length];
        for(int i = 0; i <= lim; i++) {
            System.arraycopy(arr,i,tmpArr,0,subarr.length);
            if(Arrays.equals(tmpArr,subarr))
                return true;
        }
        return false;
    }
}
