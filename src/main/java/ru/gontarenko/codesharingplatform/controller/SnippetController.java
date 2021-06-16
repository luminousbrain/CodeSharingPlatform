package ru.gontarenko.codesharingplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.gontarenko.codesharingplatform.enitity.CodeSnippet;
import ru.gontarenko.codesharingplatform.enitity.ProgrammingLanguage;
import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.exception.SnippetException;
import ru.gontarenko.codesharingplatform.repository.ProgramingLanguageRepository;
import ru.gontarenko.codesharingplatform.service.SnippetService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/snippet")
public class SnippetController {
    private final SnippetService snippetService;
    private final List<ProgrammingLanguage> programmingLanguages;

    @Autowired
    public SnippetController(ProgramingLanguageRepository programingLanguageRepo, SnippetService snippetService) {
        this.snippetService = snippetService;
        programmingLanguages = programingLanguageRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


    @GetMapping("/{link}")
    public String snippet(
            @PathVariable String link,
            Model model,
            @AuthenticationPrincipal User user
    ) {
        CodeSnippet snippet = snippetService.findByLink(link);
        if (snippet.isHidden()) {
            if (!user.getId().equals(snippet.getUser().getId())) {
                return "not_found";
            }
        }
        model.addAttribute("snippet", snippet);
        return "snippet/snippet";
    }


    @GetMapping("/new")
    public String createSnippet(
            @ModelAttribute("snippet") CodeSnippet snippet,
            Model model,
            @AuthenticationPrincipal User user
    ) {
        snippet.setUser(user);
        if (snippet.getProgrammingLanguage() == null) {
            snippet.setProgrammingLanguage(programmingLanguages.get(0));
        }
        model.addAttribute("progLangList", programmingLanguages);
        return "snippet/snippet_new";
    }

    @PostMapping("/new")
    public String createSnippet(
            @ModelAttribute("snippet") @Valid CodeSnippet snippet,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "snippet/snippet_new";
        }
        try {
            snippetService.save(snippet, bindingResult);
        } catch (SnippetException e) {
            bindingResult.addError(new FieldError("snippet", "link", "link already taken"));
            return "snippet/snippet_new";
        }
        return "redirect:/snippet/" + snippet.getLink();
    }

    @DeleteMapping("/{link}")
    public String delete(
            @PathVariable("link") String link,
            @AuthenticationPrincipal User user
    ) {
        snippetService.delete(link, user);
        return "redirect:/profile";
    }
}
