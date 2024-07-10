package ex01;

public class Egg implements Runnable {
    private final int count;
    private final String name;
    Thread thread;

    public Egg(int count, String name) {
        this.count = count;
        this.name = name;
        thread = new Thread(this);
        thread.setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < this.count; i++) {
            Program.outputAndNotify("Hen", "Egg");
        }
    }
}
