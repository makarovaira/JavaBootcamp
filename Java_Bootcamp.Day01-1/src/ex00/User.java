package ex00;

public class User {
    private int id;
    private String name;
    private int balance;

    public User(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        SetValidBalance(balance);
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


    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }
 
    public void setBalance(int balance) {
        this.balance = balance;
    }

}
