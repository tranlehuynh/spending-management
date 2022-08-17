package com.app.repository;

import com.app.pojo.Transaction;
import java.util.List;
import java.util.Map;

public interface TransactionRepository {
    List<Transaction> getTransactions(Map<String, String> params, int page);
    
    int countTransactions();
    
    boolean addTransaction(Transaction t);
}
