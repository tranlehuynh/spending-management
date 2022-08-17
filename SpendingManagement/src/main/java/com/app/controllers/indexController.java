package com.app.controllers;

import com.app.pojo.Transaction;
import com.app.pojo.User;
import com.app.pojo.UserWallet;
import com.app.service.CategoryService;
import com.app.service.ItemService;
import com.app.service.TransactionService;
import com.app.service.UserWalletService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        if (isAuthenticated())
            return "redirect:/dashboard";
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam Map<String, String> params, HttpSession session) {
        
        if (!isAuthenticated())
            return "redirect:/";
        
        List<String> categories = new ArrayList<>();
        model.addAttribute("categories", this.categoryService.getCategories());

        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        List<String> items = new ArrayList<>();
        model.addAttribute("items", this.itemService.getItems(params, page));

        List<String> transactions = new ArrayList<>();
        model.addAttribute("transactions", this.transactionService.getTransactions(params, page));
   
        model.addAttribute("userWallets", this.userWalletService.getUserWallets());
        model.addAttribute("countTransactions", this.transactionService.countTransaction());
        model.addAttribute("pageSize", Integer.parseInt(env.getProperty("page.size")));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "index";
    }

    @GetMapping("/account-details")
    public String accountDetails() {
        return "account-details";
    }

    @PostMapping("/transaction")
    public String addTransaction(@ModelAttribute(value = "transaction") @Valid Transaction p,
            BindingResult rs, Model model) {

        for (int i = 0; i < this.itemService.getItemsNo().size(); i++) {
            if (p.getTemp().equals(this.itemService.getItemsNo().get(i).getName())) {
                p.setItemId(this.itemService.getItemsNo().get(i));
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
