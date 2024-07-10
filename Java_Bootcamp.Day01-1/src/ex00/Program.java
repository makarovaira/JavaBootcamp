package ex00;

public class Program {
    public static void main(String[] args) {

        User u1 = new User(0, "Mike", 23);
        User u2 = new User(1, "Ivan", 45);
        User u3 = new User(2, "Anna", 37);
        System.out.println(u1.getName());
        u1.ReturnUserInfo();
        Transaction transaction1 = new Transaction(u1, u2, Transaction.CategoryOfTransfer.CREDIT, -20);
        Transaction transaction2 = new Transaction(u3, u1, Transaction.CategoryOfTransfer.DEBIT, 12);
        transaction1.ReturnTransactionInfo();
    }
}
