package ex04;

public class Program {
    public static void main(String[] args) {
        User u1 = new User("Mike", 23);
        User u2 = new User( "Ivan", 45);
        User u3 = new User( "Anna", 37);
        User u4 = new User("Jack", 74);

        TransactionsService transactionsService = new TransactionsService();

        transactionsService.addUser(u1);
        transactionsService.addUser(u2);
        transactionsService.addUser(u3);
        transactionsService.addUser(u4);

        transactionsService.executionOfTransaction(u1.getId(), u2.getId(), 10);
        transactionsService.executionOfTransaction(u3.getId(), u1.getId(), 13);
        transactionsService.executionOfTransaction(u4.getId(), u2.getId(), 23);
        System.out.println();
        System.out.println("User1");
        u1.returnTransactionsList();

        Transaction transactionError = new Transaction(u1, u2, Transaction.CategoryOfTransfer.CREDIT, -20);
        u1.getTransactionsByUser().addTransaction(transactionError);
        Transaction[] unpairedTransactions = transactionsService.getUnpairedTransactions();
        System.out.println();
        System.out.println("Unpaired Array");
        for (int i = 0; i < unpairedTransactions.length; i++) {
            unpairedTransactions[i].returnTransactionInfo();
        }
        System.out.println();
        System.out.println("Array before deleting transaction");
        u1.returnTransactionsList();
        transactionsService.deleteTransFromList(transactionError.getId(), u1.getId());
        System.out.println();
        System.out.println("Array after deleting transaction");
        u1.returnTransactionsList();
    }
}
