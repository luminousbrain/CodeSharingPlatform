package ru.gontarenko.code.sharing.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gontarenko.code.sharing.platform.service.SnippetService;

@Controller
public class HomeController {
    private final SnippetService snippetService;

    @Autowired
    public HomeController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping(value = {"/home", "/"})
    public String home(Model model) {
        model.addAttribute("snippetList", snippetService.findAll());
        return "home";
    }
}