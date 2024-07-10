package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private static File dir;
    private static String startLen = "--current-folder=";

    public static void main(String[] args) {
        if (args.length != 1 && !args[0].startsWith(startLen)) {
            System.exit(-1);
        }
        String path = args[0].replaceFirst(startLen, "");
        if (path.length() == 0) {
            System.out.println("No such file or directory");
        }
        dir = new File(path);
        System.out.println(dir.getPath());
        try (Scanner scan = new Scanner(System.in)) {
            String inputLine;
            while (!(inputLine = scan.nextLine()).equals("exit")) {
                if (!inputLine.isEmpty()) {
                    processing(inputLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processing(String inputStr) {
        String[] inputArgs = inputStr.split("\\s+");
        switch (inputArgs[0]) {
            case "ls":
                processLs(inputArgs);
                break;
            case "cd":
                processCd(inputArgs);
                break;
            case "mv":
                processMv(inputArgs);
                break;
            default:
                System.out.println("Unknown command");
        }

    }

    private static void processLs(String[] inputArgs) {
        if (inputArgs.length == 1) {
            File[] files = dir.listFiles();
            if (files == null) {
                files = new File[0];
            }
            for (File file : files) {
                System.out.println(file.getName() + " " + (calcFileSize(file) / 1000) + " KB");
            }
        }  else {
            System.out.println("ls: Invalid number of arguments");
        }
    }

    private static void processCd(String[] inputArgs) {
        if (inputArgs.length == 2) {
            String newPath = resolvePath(inputArgs[1]);
            File newDirectory = new File(newPath);
            if (newDirectory.isDirectory()) {
                dir = newDirectory;
                System.out.println(newDirectory.getAbsolutePath());
            } else {
                System.out.println("cd: no such directory " + inputArgs[1]);
            }
        } else {
            System.out.println("cd: Invalid number of arguments");
        }
    }

    private static void processMv(String[] inputArgs) {
        if (inputArgs.length == 3) {
            String sourcePath = resolvePath(inputArgs[1]);
            String destinationPath = resolvePath(inputArgs[2]);

            File sourceFile = new File(sourcePath);
            File destinationDir = new File(destinationPath);

            String destinationFilePath = destinationPath;

            if (destinationDir.isDirectory()) {
                destinationFilePath = destinationPath + File.separator + sourceFile.getName();
            }

            try {
                if (sourceFile.exists()) {
                    Files.move(Paths.get(sourcePath), Paths.get(destinationFilePath));
                } else {
                    System.err.println("mv: File not found");
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("mv: Invalid number of arguments");
        }
    }

    private static String resolvePath(String name) {
        if (name.startsWith("/")) {
            return name;
        } else if (name.startsWith("./")) {
            return dir + name.substring(1);
        } else if (name.startsWith("..")) {
            return dir.getParent() + name.replaceFirst(("\\.\\."), "");
        }
        return dir + "/" + name;
    }

    private static long calcFileSize(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        long size = 0;
        File[] filesArr = file.listFiles();
        if (filesArr != null) {
            for (File src : filesArr) {
                size += calcFileSize(src);
            }
        }
        return size;
    }
}
