package com.app.service.impl;

import com.app.pojo.UserWallet;
import com.app.repository.UserWalletRepository;
import com.app.service.UserWalletService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserWalletServiceImpl implements UserWalletService {
    @Autowired
    private UserWalletRepository userWalletRepository;

    @Override
    public List<UserWallet> getUserWallets() {
        return this.userWalletRepository.getUserWallets();
    }

    @Override
    public void addUserWallet(UserWallet uw) {
        this.userWalletRepository.addUserWallet(uw);
    }
}
