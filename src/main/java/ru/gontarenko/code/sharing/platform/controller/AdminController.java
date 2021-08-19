package ru.gontarenko.code.sharing.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gontarenko.code.sharing.platform.security.AccountStatus;
import ru.gontarenko.code.sharing.platform.service.SnippetService;
import ru.gontarenko.code.sharing.platform.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final SnippetService snippetService;

    @Autowired
    public AdminController(UserService userService, SnippetService snippetService) {
        this.userService = userService;
        this.snippetService = snippetService;
    }

    @GetMapping("/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "admin/users";
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
    public String userSnippets(@PathVariable Long id, Model model) {
        model.addAttribute("userSnippets", snippetService.findAllByUser(userService.findById(id)));
        return "admin/user_snippets";
    }
}