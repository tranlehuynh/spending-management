package com.app.controllers;

import com.app.pojo.Google;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
//    @Autowired
//    private MailSender mailSender;

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
    public String index(HttpServletRequest request, Model model) throws ClientProtocolException, IOException {
        if (isAuthenticated()) {
            return "redirect:/dashboard";
        }

        String code = request.getParameter("code");

        if (code != null) {
            String accessToken = userService.getToken(code);
            Google googlePojo = userService.getUserInfo(accessToken);
            UserDetails userDetail = userService.buildUser(googlePojo);

            int count = 0;

            for (int i = 0; i < this.userService.getAllUsers().size(); i++) {
                if (googlePojo.getEmail().equals(this.userService.getAllUsers().get(i).getEmail())) {
                    count = 1;
                }
            }

            User user = new User();
            user.setEmail(googlePojo.getEmail());
            user.setFirstName("Google");
            user.setLastName("User");
            user.setPassword("123");

            if (count == 0) {

                this.userService.addUser(user);

            }

//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null,
//                    userDetail.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/";
        }

        return "login";
    }

    @GetMapping("/dashboard/wallet-user")
    public String walletUser(Model model, @RequestParam(required = false, name = "kw") String kw, HttpSession session) {
        if (!isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("showNhe", 1);

        if (kw != null) {
            for (int i = 0; i < this.userService.getAllUsers().size(); i++) {
                if (kw.equals(this.userService.getAllUsers().get(i).getEmail())) {
                    model.addAttribute("userGetByEmail", this.userService.getAllUsers().get(i));
                }
            }
        }

        User user = (User) session.getAttribute("currentUser");

        List<Transaction> myTransaction = new ArrayList<Transaction>();

        for (int i = 0; i < this.transactionService.getAllTransactions().size(); i++) {
            if (Objects.equals(this.transactionService.getAllTransactions().get(i).getWalletId().getOwner(), user.getId()) && this.transactionService.getAllTransactions().get(i).getPending() == 2) {
                myTransaction.add(this.transactionService.getAllTransactions().get(i));
            }
        }

        model.addAttribute("myTransactions", myTransaction);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userWallets", this.userWalletService.getUserWallets());

        return "wallet-user";
    }

    @PostMapping("/dashboard/wallet-user")
    public String addAgain(@ModelAttribute(value = "myId") @Valid UserWallet p, BindingResult rs, @ModelAttribute(value = "walletUserForm") @Valid Transaction t) {
        if (rs.hasErrors()) {
            return "index";
        }

        int count = 0;

        for (int i = 0; i < this.userService.getAllUsers().size(); i++) {
            if (p.getMyUserId() == this.userService.getAllUsers().get(i).getId()) {
                p.setUserId(this.userService.getAllUsers().get(i));
                break;
            }
        }

        for (int i = 0; i < this.walletService.getWallets().size(); i++) {
            if (p.getMyWalletId() == this.walletService.getWallets().get(i).getId()) {
                p.setWalletId(this.walletService.getWallets().get(i));
                break;
            }
        }

        for (int i = 0; i < this.userWalletService.getUserWallets().size(); i++) {
            if (p.getMyUserId() == this.userWalletService.getUserWallets().get(i).getUserId().getId() && p.getMyWalletId() == this.userWalletService.getUserWallets().get(i).getWalletId().getId()) {
                count++;
                break;
            }
        }

        if (count == 0) {
            this.userWalletService.addUserWallet(p);
        }

        return "wallet-user";
    }


    @PostMapping("/dashboard/wallet-user/transaction")
    public String newIndex(@ModelAttribute(value = "walletUserForm") @Valid Transaction t, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            return "login";
        }

        if (t.getWalletUserAccept() != 0) {
            transactionService.updateTransaction(t.getWalletUserAccept());
            return "redirect:/dashboard/wallet-user";
        }

        if (t.getWalletUserDelete() != 0) {
            int id = t.getWalletUserDelete();
            transactionService.deleteTransaction(id);
            return "redirect:/dashboard/wallet-user";
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

        userService.sendEmail("1951052079huynh@gmail.com", "tranlehuynhh@gmail.com", "TEST", "Test send email");
        //Show first wallet when login
        if (view == null) {
            for (int i = 0; i < userWalletService.getUserWallets().size(); i++) {
                if (Objects.equals(userWalletService.getUserWallets().get(i).getUserId().getId(), user.getId())) {
                    view = String.valueOf(userWalletService.getUserWallets().get(i).getWalletId().getId());
                    break;
                }
            }
        }

        //Show "there no wallet" when dont have wallet
        int countUserWallet = 0;
        for (int i = 0; i < walletService.getWallets().size(); i++) {
            if (Objects.equals(user.getId(), walletService.getWallets().get(i).getOwner())) {
                countUserWallet++;
            }
        }
        model.addAttribute("showWallet", countUserWallet);

        //Get inflow and outflow
        if (view != null) {
            for (int i = 0; i < this.transactionService.getAllTransactions().size(); i++) {
                if (this.transactionService.getAllTransactions().get(i).getWalletId().getId() == Integer.parseInt(view)) {
                    if (this.transactionService.getAllTransactions().get(i).getItemId().getId() <= 10 && transactionService.getAllTransactions().get(i).getPending() == 1) {
                        outflow += this.transactionService.getAllTransactions().get(i).getAmount();
                    } else if (this.transactionService.getAllTransactions().get(i).getItemId().getId() >= 10 && transactionService.getAllTransactions().get(i).getPending() == 1) {
                        inflow += this.transactionService.getAllTransactions().get(i).getAmount();
                    }
                    countTransactionsOfUser++;
                }
            }
        }

        double firstWallet = 0;

//        for (int i = 0; i < this.userWalletService.getUserWallets().size(); i++) {
//            if (userWalletService.getUserWallets().get(i) != null) {
//                if (Objects.equals(this.userWalletService.getUserWallets().get(i).getUserId().getId(), user.getId())) {
//                    firstWallet = this.userWalletService.getUserWallets().get(i).getWalletId().getTotalMoney();
//                    break;
//                }
//            }
//        }
        double total = inflow - outflow;

        model.addAttribute("view", view);
        model.addAttribute("userWallets", this.userWalletService.getUserWallets());
        model.addAttribute("inflow", inflow);
        model.addAttribute("outflow", outflow);
        model.addAttribute("total", total);
        model.addAttribute("firstWallet", firstWallet);
        model.addAttribute("transactions", this.transactionService.getTransactions(params, page, view));
        model.addAttribute("countTransactions", this.transactionService.countTransaction());
        model.addAttribute("countTransactionsOfUser", countTransactionsOfUser);
        model.addAttribute("allOfTransactions", this.transactionService.getAllTransactions());
        model.addAttribute("pageSize", Integer.parseInt(env.getProperty("page.size")));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "index";
    }

    @GetMapping("/account-details")
    public String accountDetails(Model model, HttpSession session) {
        model.addAttribute("showNha", 2);
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        User user = (User) session.getAttribute("currentUser");

        for (int i = 0; i < userService.getAllUsers().size(); i++) {
            if (Objects.equals(user.getId(), userService.getAllUsers().get(i).getId())) {
                model.addAttribute("userDetail", userService.getAllUsers().get(i));
            }
        }
        return "account-details";
    }

    @PostMapping("/account-details/userinfo")
    public String updateUserInfo(Model model, @ModelAttribute(value = "userInfo") @Valid User u, BindingResult rs, HttpSession session) {
        if (rs.hasErrors()) {
            return "index";
        }

        User user = (User) session.getAttribute("currentUser");

        if (userService.updateUser(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone(), user.getId()) == true) {
            return "redirect:/account-details";
        }

        return "account-details";
    }

    @PostMapping("/addWallet")
    public String addMyWallet(@ModelAttribute(value = "addWallet") @Valid Wallet p, HttpSession session,
            BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            return "index";
        }

        if (p.getName() != null && p.getTotalMoney() != null) {
            int count = this.walletService.countWallets();
            User user1 = new User();

            for (int i = 0; i < this.userService.countUsers(); i++) {
                if (Integer.parseInt(p.getUserWalletTemp()) == (this.userService.getAllUsers().get(i).getId())) {
                    user1 = this.userService.getAllUsers().get(i);
                }
            }

            User user = (User) session.getAttribute("currentUser");
            p.setOwner(user.getId());

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
        }

        return "index";
    }

    @PostMapping("/transaction")
    public String addTransaction(@ModelAttribute(value = "transaction") @Valid Transaction p,
            BindingResult rs, Model model, HttpSession session) {

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

        User newUser = (User) session.getAttribute("currentUser");
        if (Objects.equals(newUser.getId(), p.getWalletId().getId())) {
            p.setPending(1);
        } else {
            p.setPending(2);
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
