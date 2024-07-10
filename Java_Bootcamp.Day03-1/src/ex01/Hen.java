package ex01;

public class Hen implements Runnable {
    private final int count;
    private final String name;
    Thread thread;

    public Hen(int count, String name) {
        this.count = count;
        this.name = name;
        thread = new Thread(this);
        thread.setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < this.count; i++) {
            Program.outputAndNotify("Egg", "Hen");
        }
    }
}
