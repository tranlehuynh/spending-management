package com.app.service;

import com.app.pojo.UserWallet;
import java.util.List;

public interface UserWalletService {
    List<UserWallet> getUserWallets();
    void addUserWallet(UserWallet uw);
}
