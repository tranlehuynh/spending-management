package com.app.service.impl;

import com.app.pojo.Wallet;
import com.app.repository.WalletRepository;
import com.app.service.WalletService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletServiceImpl implements WalletService{
    
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> getWallets() {
        return this.walletRepository.getWallets();
    }

    @Override
    public boolean addWallet(Wallet w) {
        return this.walletRepository.addWallet(w);
    }

    @Override
    public int countWallets() {
        return this.walletRepository.countWallets();
    }

    @Override
    public boolean checkWalletOwnerExists(int userId) {
        return this.walletRepository.checkWalletOwnerExists(userId);
    }
   
}
