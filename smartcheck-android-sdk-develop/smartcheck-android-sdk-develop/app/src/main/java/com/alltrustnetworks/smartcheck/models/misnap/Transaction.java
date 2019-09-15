package com.alltrustnetworks.smartcheck.models.misnap;

import com.squareup.moshi.Json;
import org.parceler.Parcel;

@Parcel
public class Transaction {
    public @Json(name = "Transaction") TransactionData transactionData;

    public Transaction() {
    }

    public Transaction(TransactionData transactionData) {
        this.transactionData = transactionData;
    }

    public TransactionData getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionData transactionData) {
        this.transactionData = transactionData;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionData=" + transactionData +
                '}';
    }
}
