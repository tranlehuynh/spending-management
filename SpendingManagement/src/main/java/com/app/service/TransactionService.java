package com.app.service;

import com.app.pojo.Transaction;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    List<Transaction> getTransactions();

    List<Transaction> getTransactionsPagination(Map<String, String> params, int page, String string);
    
    Transaction getLastTransaction();

    List<Object[]> countTransactionsByItem();

    int countTransaction();

    boolean addTransaction(Transaction transaction);

    void updateTransaction(int id);
    
    void updateTransactionPending2(int id);

    void deleteTransactionById(int id);

    void deleteTransactionByWalletId(int walletId);

}
