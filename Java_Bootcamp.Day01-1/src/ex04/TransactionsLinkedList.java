package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction first;
    private Transaction last;
    private int size = 0;

    @Override
    public void addTransaction(Transaction transactionToAdd) {
        if (first == null) {
            first = transactionToAdd;
        } else {
            last.setNextObject(transactionToAdd);
        }
        last = transactionToAdd;
        size += 1;
    }

    @Override
    public void deleteTransactionById(UUID id) {
        Transaction previous;
        Transaction next;
        if (first != null) {
            next = first.getNextObject();
            previous = first;
            if (id == previous.getId()) {
                first = next;
                size -= 1;
                return;
            }
            while (next != null) {
                if (next.getId() == id) {
                    previous.setNextObject(next.getNextObject());
                    size -= 1;
                    return;
                }
                previous = previous.getNextObject();
                next = next.getNextObject();
            }
        }
        throw new TransactionNotFoundException("The transaction with this id was not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction trans = first;
        Transaction[] arrayTrans = new Transaction[size];
        for (int i = 0; i < this.size; i++) {
            arrayTrans[i] = trans;
            trans = trans.getNextObject();
        }
        return arrayTrans;
    }

    public Transaction getFirst() {
        return first;
    }

    public Transaction getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public void returnTransactionListInfo() {
        Transaction tr = first;
        while (tr != null) {
            tr.returnTransactionInfo();
            tr = tr.getNextObject();
        }
    }
}
