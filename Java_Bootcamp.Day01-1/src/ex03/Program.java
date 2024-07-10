package ex03;

public class Program {
    public static void main(String[] args) {
        User u1 = new User("Mike", 23);
        User u2 = new User( "Ivan", 45);
        User u3 = new User( "Anna", 37);

        Transaction transaction1 = new Transaction(u1, u2, Transaction.CategoryOfTransfer.CREDIT, -20);
        Transaction transaction2 = new Transaction(u3, u1, Transaction.CategoryOfTransfer.DEBIT, 12);
        Transaction transaction3 = new Transaction(u3, u1, Transaction.CategoryOfTransfer.CREDIT, -8);
        TransactionsLinkedList transactionsList = new TransactionsLinkedList();
        transactionsList.addTransaction(transaction1);
        transactionsList.addTransaction(transaction2);
        transactionsList.returnTransactionListInfo();
        System.out.println();
        System.out.println("Size of the linked list before add the element = " + transactionsList.getSize());
        transactionsList.addTransaction(transaction3);
        System.out.println("Size of the linked list after add the element = " + transactionsList.getSize());
        System.out.println();
        System.out.println("Size of the linked list before deleting the element = " + transactionsList.getSize());
        transactionsList.deleteTransactionById(transaction2.getId());
        System.out.println("Size of the linked list after deleting the element = " + transactionsList.getSize());

        Transaction[] transArray = transactionsList.toArray();
        System.out.println();
        for (int i = 0; i < transactionsList.getSize(); i++){
            transArray[i].ReturnTransactionInfo();
        }
    }
}
