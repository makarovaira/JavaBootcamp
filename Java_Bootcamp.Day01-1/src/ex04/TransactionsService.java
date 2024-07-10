package ex04;

import java.util.UUID;

public class TransactionsService {
    UsersList usersList = new UsersArrayList();

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public int getUserBalance(User user) {
        return user.getBalance();
    }

    public int getUserBalanceById(int id) {
        return usersList.getUserById(id).getBalance();
    }

    public void executionOfTransaction(int senderId, int recipientId, int transferAmount) {
        User sender = usersList.getUserById(senderId);
        User recipient = usersList.getUserById(recipientId);
        checkAmount(sender, recipient, transferAmount);

        Transaction debitTransaction = new Transaction(sender, recipient, Transaction.CategoryOfTransfer.DEBIT, transferAmount);
        Transaction creditTransaction = new Transaction(sender, recipient, Transaction.CategoryOfTransfer.CREDIT, -transferAmount);

        debitTransaction.setId(creditTransaction.getId());

        sender.getTransactionsByUser().addTransaction(creditTransaction);
        recipient.getTransactionsByUser().addTransaction(debitTransaction);
        sender.SetValidBalance(sender.getBalance() - transferAmount);
        recipient.SetValidBalance(recipient.getBalance() + transferAmount);
    }

    public Transaction[] getTransactionArray(int userId) {
        return usersList.getUserById(userId).getTransactionsByUser().toArray();
    }

    public void deleteTransFromList(UUID transId, int userId) {
        usersList.getUserById(userId).getTransactionsByUser().deleteTransactionById(transId);
    }

    public Transaction[] getUnpairedTransactions() {
        TransactionsLinkedList list = new TransactionsLinkedList();
        for (int i = 0; i < usersList.getCountOfUsers(); i++) {
            User user = usersList.getUserByIndex(i);
            if (user != null) {
                int size = user.getTransactionsByUser().getSize();
                for (int j = 0; j < size; j++) {
                    list.addTransaction(user.getTransactionsByUser().toArray()[j]);
                }
            }
        }
        TransactionsLinkedList res = new TransactionsLinkedList();
        for (int i = 0; i < list.getSize(); i++) {
            int count = 0;
            for (int j = 0; j < list.getSize(); j++) {
                if (list.toArray()[i].getId() == list.toArray()[j].getId()) {
                    count++;
                }
            }
            if (count != 2) {
                res.addTransaction(list.toArray()[i]);
            }
        }
        return res.toArray();
    }


    public void checkAmount(User sender, User recipient, int amount) {
        if (amount < 0 || sender.getBalance() < amount || sender.getId() == recipient.getBalance()) {
            throw new IllegalTransactionException("Incorrect transaction arguments");
        }
    }

}
