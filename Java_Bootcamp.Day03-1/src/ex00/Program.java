package ex00;

public class Program {
    private final static String startLen = "--count=";
    private static int countThreads;

    public static void main(String[] args) {
        processingInputData(args);
        try {
            NewThread egg = new NewThread(countThreads, "Egg");
            NewThread hen = new NewThread(countThreads, "Hen");
            egg.start();
            hen.start();
            egg.join();
            hen.join();
            for (int i = 0; i < countThreads; i++) {
                System.out.println("Human");
            }
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
}
