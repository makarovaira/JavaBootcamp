package ex05;

public class Program {
    public static void main(String[] args) {
        boolean ifDevMode = false;

        if (args.length > 0 && "--profile=dev".equals(args[0])) {
            ifDevMode = true;
        }

        Menu menu = new Menu(ifDevMode);
        menu.run();

    }
}
