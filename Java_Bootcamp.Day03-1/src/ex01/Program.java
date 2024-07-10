package ex01;

import ex00.NewThread;

public class Program {
    private final static String startLen = "--count=";
    private static int countThreads;
    private static String currentThread = "Egg";

    public static void main(String[] args) {
        processingInputData(args);
        try {
            Egg egg = new Egg(countThreads, "Egg");
            Hen hen = new Hen(countThreads, "Hen");

            egg.thread.start();
            hen.thread.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

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
            countThreads = count;
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void outputAndNotify(String currentThread, String nextThread) {
        synchronized (Program.class) {
            while (!Program.currentThread.equals(currentThread)) {
                try {
                    Program.class.wait();
                } catch (InterruptedException exception) {
                    System.err.println("Error: " + exception.getMessage());
                }
            }
            System.out.println(currentThread);
            Program.currentThread = nextThread;
            Program.class.notifyAll();
        }
    }
}
