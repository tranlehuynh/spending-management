package com.app.service.impl;

import com.app.pojo.Transaction;
import com.app.repository.TransactionRepository;
import com.app.service.ItemService;
import com.app.service.TransactionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ItemService itemService;

    @Override
    public List<Transaction> getTransactionsPagination(Map<String, String> params, int page, String ghep) {
        return this.transactionRepository.getTransactionsPagination(params, page, ghep);
    }

    @Override
    public int countTransaction() {
        return this.transactionRepository.countTransactions();
    }

    @Override
    public boolean addTransaction(Transaction t) {
        return this.transactionRepository.addTransaction(t);
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactionRepository.getTransactions();
    }

    @Override
    @Modifying
    public void updateTransaction(int id) {
        this.transactionRepository.updateTransaction(id);
    }

    @Override
    @Modifying
    public void deleteTransactionById(int id) {
        this.transactionRepository.deleteTransactionById(id);
    }

    @Override
    public List<Object[]> countTransactionsByItem() {
        return transactionRepository.countTransactionsByItem();
    }

    @Override
    public void deleteTransactionByWalletId(int id) {
        this.transactionRepository.deleteTransactionByWalletId(id);
    }


    @Override
    public void updateTransactionPending2(int id) {
        this.transactionRepository.updateTransactionPending2(id);
    }

    @Override
    public Transaction getLastTransaction() {
        return this.transactionRepository.getLastTransaction();
    }
}
