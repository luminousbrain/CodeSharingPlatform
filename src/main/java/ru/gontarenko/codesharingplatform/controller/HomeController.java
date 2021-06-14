package ru.gontarenko.codesharingplatform.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gontarenko.codesharingplatform.enitity.User;

@Controller
public class HomeController {

    @GetMapping(value = {"/home", "/"})
    public String home(@AuthenticationPrincipal User user) {
        System.out.println(user);
        return "home";
    }
}
