package com.app.repository;

import com.app.pojo.Wallet;
import java.util.List;

public interface WalletRepository {
    List<Wallet> getWallets();
    int countWallets();
    boolean addWallet(Wallet w);
}
