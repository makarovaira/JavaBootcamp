package ex05;

import java.util.UUID;

public class Transaction {

    private UUID id;
    private User recipient;
    private User sender;
    private CategoryOfTransfer category;
    private int transferAmount;
    private Transaction nextObject;

    public Transaction(User recipient, User sender, CategoryOfTransfer category, int transferAmount) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        checkTransferAmount(transferAmount);
    }

    public void checkTransferAmount(int transferAmount) {
        if ((category == CategoryOfTransfer.CREDIT && transferAmount > 0) ||
                (category == CategoryOfTransfer.DEBIT && transferAmount < 0)) {
            System.err.print("Invalid transaction category");
        } else if ((category == CategoryOfTransfer.CREDIT && sender.getBalance() < -transferAmount) ||
                (category == CategoryOfTransfer.DEBIT && sender.getBalance() < transferAmount)) {
            System.err.print("Insufficient funds to complete the transaction");
        } else {
            this.transferAmount = transferAmount;
        }
    }

    public void returnTransactionInfo() {
        System.out.println("Transaction ID: " + id);
        System.out.println("Sender: " + sender.getName());
        System.out.println("Recipient: " + recipient.getName());
        System.out.println("Transfer Category: " + category);
        System.out.println("Transfer Amount: " + transferAmount);
    }

    enum CategoryOfTransfer{
        DEBIT,
        CREDIT
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public CategoryOfTransfer getCategory() {
        return category;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public Transaction getNextObject() { return nextObject; }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setCategory(CategoryOfTransfer category) {
        this.category = category;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public void setNextObject(Transaction nextObject) {this.nextObject = nextObject;}
}
