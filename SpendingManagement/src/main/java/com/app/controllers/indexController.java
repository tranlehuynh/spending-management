package com.app.controllers;

import com.app.pojo.Transaction;
import com.app.pojo.User;
import com.app.pojo.UserWallet;
import com.app.pojo.Wallet;
import com.app.service.CategoryService;
import com.app.service.ItemService;
import com.app.service.TransactionService;
import com.app.service.UserService;
import com.app.service.UserWalletService;
import com.app.service.WalletService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
@PropertySource("classpath:messages.properties")
public class IndexController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;
    @Autowired
    Environment env;

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @RequestMapping("/")
    public String index() {
        if (isAuthenticated()) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/dashboard/wallet-user")
    public String walletUser(Model model, @RequestParam(required = false, name = "kw") String kw) {
        if (!isAuthenticated()) {
            return "redirect:/";
        }

        if (kw != null) {
            for (int i = 0; i < this.userService.getAllUsers().size(); i++) {
                if (kw.equals(this.userService.getAllUsers().get(i).getEmail())) {
                    model.addAttribute("userGetByEmail", this.userService.getAllUsers().get(i));
                }
            }
        }

        return "wallet-user";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam Map<String, String> params, HttpSession session, @RequestParam(required = false, name = "wallet") String view) {

        if (!isAuthenticated()) {
            return "redirect:/";
        }

        List<String> categories = new ArrayList<>();
        model.addAttribute("categories", this.categoryService.getCategories());

        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        List<String> items = new ArrayList<>();
        model.addAttribute("items", this.itemService.getItems(params, page));

        List<String> transactions = new ArrayList<>();

        //Get current user
        User user = (User) session.getAttribute("currentUser");

        int countTransactionsOfUser = 0;
        double inflow = 0;
        double outflow = 0;

        if (view == null) {
            view = "1";
        }

        for (int i = 0; i < this.transactionService.getAllTransactions().size(); i++) {
            if (this.transactionService.getAllTransactions().get(i).getWalletId().getId() == Integer.parseInt(view)) {
                if (this.transactionService.getAllTransactions().get(i).getItemId().getId() <= 10) {
                    outflow += this.transactionService.getAllTransactions().get(i).getAmount();
                } else {
                    inflow += this.transactionService.getAllTransactions().get(i).getAmount();
                }
                countTransactionsOfUser++;
            }
        }

        double firstWallet = 0;

//        for (int i = 0; i < this.userWalletService.getUserWallets().size(); i++) {
//            if (Objects.equals(this.userWalletService.getUserWallets().get(i).getUserId().getId(), user.getId())) {
//                firstWallet = this.userWalletService.getUserWallets().get(i).getWalletId().getTotalMoney();
//                break;
//            }
//        }
        double total = (firstWallet + inflow) - outflow;

        model.addAttribute("view", view);
        model.addAttribute("userWallets", this.userWalletService.getUserWallets());
        model.addAttribute("inflow", firstWallet + inflow);
        model.addAttribute("outflow", outflow);
        model.addAttribute("total", total);
        model.addAttribute("firstWallet", total);
        model.addAttribute("transactions", this.transactionService.getTransactions(params, page, view));
        model.addAttribute("countTransactions", this.transactionService.countTransaction());
        model.addAttribute("countTransactionsOfUser", countTransactionsOfUser);
        model.addAttribute("allOfTransactions", this.transactionService.getAllTransactions());
        model.addAttribute("pageSize", Integer.parseInt(env.getProperty("page.size")));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "index";
    }

    @GetMapping("/account-details")
    public String accountDetails() {
        return "account-details";
    }

    @PostMapping("/addWallet")
    public String addMyWallet(@ModelAttribute(value = "addWallet") @Valid Wallet p,
            BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            return "index";
        }

        int count = this.walletService.countWallets();
        User user1 = new User();

        for (int i = 0; i < this.userService.countUsers(); i++) {
            if (Integer.parseInt(p.getUserWalletTemp()) == (this.userService.getAllUsers().get(i).getId())) {
                user1 = this.userService.getAllUsers().get(i);
            }
        }

        if (this.walletService.addWallet(p) == true) {
            Wallet wallet = new Wallet();
            for (int i = this.walletService.getWallets().size() - 1; i < this.walletService.getWallets().size(); i++) {
                wallet = this.walletService.getWallets().get(i);
            }

            UserWallet newUserWallet = new UserWallet();
            newUserWallet.setWalletId(wallet);
            newUserWallet.setUserId(user1);

            this.userWalletService.addUserWallet(newUserWallet);
            return "redirect:/dashboard";
        }

        return "index";
    }

    @PostMapping("/transaction")
    public String addTransaction(@ModelAttribute(value = "transaction") @Valid Transaction p,
            BindingResult rs, Model model) {

        for (int i = 0; i < this.itemService.getItemsNo().size(); i++) {
            if (p.getTemp().equals(this.itemService.getItemsNo().get(i).getName())) {
                p.setItemId(this.itemService.getItemsNo().get(i));
            }

        }

        for (int i = 0; i < this.walletService.getWallets().size(); i++) {
            if (Integer.parseInt(p.getWalletTemp()) == this.walletService.getWallets().get(i).getId()) {
                p.setWalletId(this.walletService.getWallets().get(i));
            }
        }

        if (rs.hasErrors()) {
            return "index";
        }

        if (this.transactionService.addTransaction(p) == true) {
            return "redirect:/dashboard";
        }
        return "index";
    }

}
