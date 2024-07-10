package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService service;
    private Scanner scanner;
    private boolean ifDevMode;

    public Menu(boolean ifDevMode) {
        service = new TransactionsService();
        scanner = new Scanner(System.in);
        this.ifDevMode = ifDevMode;
    }

    public void run() {
        while (true) {
            returnMenu();
            int choice = checkChoiceOption();
            if (choice == 1) {
                addUser();
            } else if (choice == 2) {
                getBalance();
            } else if (choice == 3) {
                addTransaction();
            } else if (choice == 4) {
                showTransactions();
            } else if (choice == 5) {
                deleteTransaction();
            } else if (choice == 6) {
                checkTransaction();;
            } else if (choice == 7) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice");
            }
            System.out.println("-------------------------------------------------------");
        }
    }

    public void returnMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (ifDevMode) {
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
        }
        System.out.println("7. Finish execution");
    }

    private boolean checkInputCount(int count) {
        boolean flag = false;
        if (count > 0 && count <= 7 && ifDevMode) {
            flag = true;
        }
        if (count > 0 && count <= 5 && !ifDevMode) {
            flag = true;
        }
        return flag;
    }

    private int checkChoiceOption() {
        int choice;
        try {
            String str = scanner.nextLine();
            choice = Integer.parseInt(str);
            if (!checkInputCount(choice)) {
                throw new InvalidFormatException();
            }
        } catch (RuntimeException exception) {
            System.out.println("Invalid input format. Enter correct number");
            choice = checkChoiceOption();
        }
        return choice;
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance: ");
        String str = scanner.nextLine();
        try {
            String[] args = str.split(" ");
            if (args.length != 2) {
                throw new InvalidFormatException("Illegal arguments");
            }
            String name = args[0];
            int balance = Integer.parseInt(args[1]);
            User user = new User(name, balance);
            service.addUser(user);
            System.out.println("User with id = " + user.getId() + " was added.");
        } catch (NumberFormatException exception) {
            System.out.println("Enter a valid balance");
        }
    }

    private void getBalance() {
        System.out.println("Enter user ID");
        String str = scanner.nextLine();
        try {
            int id = Integer.parseInt(str);
            User user = service.usersList.getUserById(id);
            System.out.println(user.getName() + " - " + user.getBalance());
        } catch (UserNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void addTransaction() {
        System.out.println("Enter a sender ID, a recipient ID and a transfer amount");
        String str = scanner.nextLine();
        try {
            String[] args = str.split(" ");
            if (args.length != 3) {
                throw new InvalidFormatException("Incorrect arguments");
            }
            int sender = Integer.parseInt(args[0]);
            int recipient = Integer.parseInt(args[1]);
            int amount = Integer.parseInt(args[2]);
            service.executionOfTransaction(sender, recipient, amount);
            System.out.println("The transfer is completed.");
        } catch (NumberFormatException exception) {
            System.out.println("Incorrect arguments");
        } catch (UserNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showTransactions() {
        System.out.println("Enter a user ID");
        String str = scanner.nextLine();
        try {
            int id = Integer.parseInt(str);
            Transaction[] transactions = service.getTransactionArray(id);
            for (Transaction trans : transactions) {
                System.out.print("To " + trans.getRecipient().getName() + "(id = " +
                        trans.getRecipient().getId() + ") ");
                System.out.print(trans.getTransferAmount() + " ");
                System.out.println("with id = " + trans.getId() + " ");
            }
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void deleteTransaction() {
        System.out.println("Enter a user ID and a transfer ID");
        String str = scanner.nextLine();
        try {
            String[] args = str.split(" ");
            if (args.length != 2) {
                throw new InvalidFormatException("Incorrect arguments");
            }
            int userId = Integer.parseInt(args[0]);
            String uuid = (args[1]);
            Transaction transToDelete = null;
            Transaction[] transactions = service.usersList.getUserById(userId).getTransactionsByUser().toArray();
            for (int i = 0; i < transactions.length; i++) {
                if (uuid.equals(transactions[i].getId().toString())) {
                    transToDelete = transactions[i];
                    break;
                }
            }
            if (transToDelete == null) {
                throw new InvalidFormatException("The transaction with this id was not found");
            }
            service.deleteTransFromList(transToDelete.getId(), userId);
            System.out.println("Transfer to " + transToDelete.getRecipient().getName() + "(id = " +
                    transToDelete.getRecipient().getId() + ") " + transToDelete.getTransferAmount() +
                    " was removed.");
        } catch (UserNotFoundException | TransactionNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (InvalidFormatException exception) {
            System.out.println("Incorrect transfer ID");
        }
    }

    private void checkTransaction() {
        System.out.println("Check results:");
        Transaction[] unpairedTrans = service.getUnpairedTransactions();
        if (unpairedTrans.length > 0) {
            for (Transaction transaction : unpairedTrans) {
                System.out.println(transaction.getRecipient().getName() +
                        " (id = " + transaction.getRecipient().getId() + ") " +
                        "has an unacknowledged transfer id = " +
                        transaction.getId() + " from " +
                        transaction.getSender().getName() + " (id = " +
                        transaction.getSender().getId() + ")" +
                        " for " + transaction.getTransferAmount());
            }
        } else {
            System.out.println("All transactions are paired.");
        }
    }
}