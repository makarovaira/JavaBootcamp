package ex04;

public class User {
    private int id;
    private String name;
    private int balance;
    private TransactionsList transactionsByUser;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        SetValidBalance(balance);
        this.transactionsByUser = new TransactionsLinkedList();
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

    public TransactionsList getTransactionsByUser() {
        return transactionsByUser;
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

    public void returnTransactionsList() {
        Transaction[] list = getTransactionsByUser().toArray();
        for (int i = 0; i < getTransactionsByUser().getSize(); i++) {
            list[i].returnTransactionInfo();
        }
    }

}