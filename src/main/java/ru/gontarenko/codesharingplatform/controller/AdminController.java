package ru.gontarenko.codesharingplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gontarenko.codesharingplatform.secutrity.AccountStatus;
import ru.gontarenko.codesharingplatform.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "admin/admin_users";
    }

    @PatchMapping("/ban/{id}")
    public String banUser(@PathVariable Long id) {
        userService.changeUserStatus(id, AccountStatus.BANNED);
        return "redirect:/admin/users";
    }

    @PatchMapping("/unban/{id}")
    public String unbanUser(@PathVariable Long id) {
        userService.changeUserStatus(id, AccountStatus.ACTIVE);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/snippets")
    public String userSnippets(@PathVariable Long id) {
        System.out.println("User id = " + id);
        return "admin/admin_user_snippets";
    }
}