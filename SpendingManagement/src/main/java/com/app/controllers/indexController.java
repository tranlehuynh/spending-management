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
import com.app.service.impl.UserServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private UserServiceImpl userServerImpl;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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
    public String index(HttpServletRequest request, Model model) throws ClientProtocolException, IOException, UsernameNotFoundException {
        if (isAuthenticated()) {
            return "redirect:/dashboard";
        }

        String code = request.getParameter("code");

        if (code != null) {
            String accessToken = userService.getToken(code);
            Google googlePojo = userService.getUserInfo(accessToken);

            int count = 0;

            for (int i = 0; i < this.userService.getAllUsers().size(); i++) {
                if (googlePojo.getEmail().equals(this.userService.getAllUsers().get(i).getEmail())) {
//                    User myUser = this.userService.getAllUsers().get(i);
                    count = 1;
                    break;
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

            List<User> users = userService.getUsersToLogin(user.getEmail());
            User myUser = users.get(0);

            if (myUser.getActive() == 2) {
                throw new UsernameNotFoundException("Users does not exist");
            }
//            UserDetails userDetail = userServerImpl.loadUserByUsername(googlePojo.getEmail());

            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), authorities);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(),
                    userDetail.getAuthorities());

//            request.getSession();
//            authentication.setDetails(new WebAuthenticationDetails(request));
            Authentication auth = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(auth);

            User u = this.userDetailsService.getUsersToLogin(auth.getName()).get(0);
            request.getSession().setAttribute("currentUser", u);

            return "redirect:/";

        }

        return "login";
    }

    @GetMapping("/report")
    public String report(Model model) {
        if (!isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("showHeader", 3);
        model.addAttribute("countTransactionsByItem", transactionService.countTransactionsByItem());
        return "report";
    }

    @PostMapping("/dashboard/deleteTransaction")
    public String deleteTrans(@ModelAttribute(value = "deleteThisTrans") @Valid Transaction t, BindingResult rs) {

        if (rs.hasErrors()) {
            return "index";
        }

        transactionService.deleteTransaction(t.getId());
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/wallet-user")
    public String walletUser(Model model, @RequestParam(required = false, name = "kw") String kw, HttpSession session,
                            @RequestParam(required = false, name = "kw") String string) {
        if (!isAuthenticated()) {
            return "redirect:/";
        }
        
        if (string != null) {
            if (string.equals("troioi")) {
                model.addAttribute("errorAdded", "You can add this");
            }
        }

        model.addAttribute("showNhe", 1);
        int temp = 0;

        if (kw != null) {
            for (int i = 0; i < this.userService.getAllUsers().size(); i++) {
                if (kw.equals(this.userService.getAllUsers().get(i).getEmail())) {
                    temp = this.userService.getAllUsers().get(i).getId();
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

        for (int i = 0; i < this.userWalletService.getUserWallets().size(); i++) {
            if (this.userWalletService.getUserWallets().get(i).getUserId().getId() == temp) {
                model.addAttribute("added", true);
            }
        }

        model.addAttribute("myTransactions", myTransaction);
        model.addAttribute("wallets", walletService.getWallets());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userWallets", this.userWalletService.getUserWallets());

        return "wallet-user";
    }

    @PostMapping("/dashboard/wallet-user")
    public String addAgain(@ModelAttribute(value = "myId") @Valid UserWallet p, BindingResult rs,
            @ModelAttribute(value = "walletUserForm") @Valid Transaction t, Model model) {

        if (rs.hasErrors()) {
            return "index";
        }
//        if (string != null) {
//            return "redirect:/dashboard";
//        }

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
            return "redirect:/dashboard/wallet-user";
        } else {
            return "redirect:/dashboard/wallet-user?kw=troioi";
        }
    }
//    
//    @GetMapping("/dashboard/wallet-user?kw=?error=error")
//    public String getErrorUserWallet(@RequestParam(required = false, name = "kw") String kw, Model model) {
//        
//        if ("error".equals(kw)) {
//            model.addAttribute("errorAdded", kw);
//        }
//        
//        return "wallet-user";
//    }

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

        //Send email
//        userService.sendEmail("1951052079huynh@gmail.com", "tranlehuynhh@gmail.com", "TEST", "Test send email");
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
//        int countUserWallet = 0;
//        for (int i = 0; i < walletService.getWallets().size(); i++) {
//            if (Objects.equals(user.getId(), walletService.getWallets().get(i).getOwner())) {
//                countUserWallet++;
//            }
//        }
//        model.addAttribute("showWallet", countUserWallet);

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
        double totalMoney = 0;

        for (int i = 0; i < walletService.getWallets().size(); i++) {
            if (Objects.equals(user.getId(), walletService.getWallets().get(i).getOwner())) {
                totalMoney = walletService.getWallets().get(i).getTotalMoney();
                break;
            }
        }

        double total = inflow - outflow;

//        if (total <= 0) {
//            userService.sendEmail("1951052079huynh@gmail.com", user.getEmail(), "WARNING", "Your inflow is lower than you outflow!");
//        } else if (totalMoney <= total) {
//            userService.sendEmail("1951052079huynh@gmail.com", user.getEmail(), "WARNING", "Your total money is higher than the wallet money!");
//        } 

        if (this.transactionService.getTransactions(params, page, view).isEmpty()) {
            model.addAttribute("hahahe", 1);
        }
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
            model.addAttribute("changeUserInfoSuccess", true);
            return "redirect:/account-details";
        }

        return "account-details";
    }

    @PostMapping("/account-details/userpassword")
    public String changeUserPassword(Model model, @ModelAttribute(value = "userPassword") @Valid User u, BindingResult rs, HttpSession session) {

        String errorMessagePassword = "";

        User user = (User) session.getAttribute("currentUser");

        if (u.getPassword().equals(u.getRetypePassword())) {
            if (userService.updatePassword(u.getPassword(), user.getId()) == true) {
                return "redirect:/logout";
            }
        } else {
            errorMessagePassword = "Your password is incorrect!";
        }

        if (rs.hasErrors()) {
            return "register";
        }

        model.addAttribute("errorMessagePassword", errorMessagePassword);

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
        int id = 0;
        p.setCreatedUser(newUser.getId());
        for (int i = 0; i < this.walletService.getWallets().size(); i++) {
            if (Objects.equals(newUser.getId(), this.walletService.getWallets().get(i).getOwner())) {
                id = this.walletService.getWallets().get(i).getId();
                break;
            }
        }
        if (Objects.equals(id, p.getWalletId().getId())) {
            p.setPending(1);
        } else {
            p.setPending(2);
        }

        LocalDate today = java.time.LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        if (p.getDate().after(date)) {
            p.setDate(date);
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
