package com.app.repository;

import com.app.pojo.Transaction;
import java.util.List;
import java.util.Map;

public interface TransactionRepository {
    List<Transaction> getTransactionsPagination(Map<String, String> params, int page, String string);
    
    List<Transaction> getTransactions();
    
    List<Object[]> countTransactionsByItem();
    
    int countTransactions();
    
    boolean addTransaction(Transaction t);
    
    void updateTransaction(int id);
    
    void deleteTransactionById(int id);
    
    void deleteTransactionByWalletId(int walletId);
    
}
