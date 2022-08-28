package com.app.service;

import com.app.pojo.Wallet;
import java.util.List;

public interface WalletService {
    List<Wallet> getWallets();
    int countWallets();
    boolean addWallet(Wallet w);
    
    boolean checkWalletOwnerExists(int userId);
}
