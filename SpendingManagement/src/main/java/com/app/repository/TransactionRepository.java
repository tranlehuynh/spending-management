package com.app.repository;

import com.app.pojo.Transaction;
import java.util.List;
import java.util.Map;

public interface TransactionRepository {
    List<Transaction> getTransactions(Map<String, String> params, int page, String ghep);
    List<Transaction> getAllTransactions();
    int countTransactions();
    boolean addTransaction(Transaction t);
    
    void updateTransaction(int id);
    
    void deleteTransaction(int id);
    
    List<Object[]> countTransactionsByItem();
}
