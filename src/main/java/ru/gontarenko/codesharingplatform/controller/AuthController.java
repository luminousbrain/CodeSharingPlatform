package ru.gontarenko.codesharingplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String tryRegister(@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        try {
            userService.save(user);
        } catch (Exception e) {
            bindingResult.addError(new FieldError("user", "email", e.getMessage()));
            return "auth/registration";
        }
        return "auth/login";
    }
}
