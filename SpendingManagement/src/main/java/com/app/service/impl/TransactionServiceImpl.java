package com.app.service.impl;

import com.app.pojo.Item;
import com.app.pojo.Transaction;
import com.app.repository.TransactionRepository;
import com.app.service.ItemService;
import com.app.service.TransactionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ItemService itemService;

    @Override
    public List<Transaction> getTransactions(Map<String, String> params, int page) {
        return this.transactionRepository.getTransactions(params, page);
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
    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.getAllTransactions();
    }
}
