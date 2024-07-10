package ex02;

public class User {
    private int id;
    private String name;
    private int balance;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        SetValidBalance(balance);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void SetValidBalance(int balance) {
        if (balance < 0) {
            System.err.println("Incorrect argument: the balance can't be negative");
            System.exit(-1);
        } else {
            this.balance = balance;
        }
    }

    public void ReturnUserInfo() {
        System.out.println("User id: " + id);
        System.out.println("User name: " + name);
        System.out.println("User balance: " + balance);
    }

}