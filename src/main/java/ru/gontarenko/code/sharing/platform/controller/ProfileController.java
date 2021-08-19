package ru.gontarenko.code.sharing.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.gontarenko.code.sharing.platform.enitity.CodeSnippet;
import ru.gontarenko.code.sharing.platform.enitity.User;
import ru.gontarenko.code.sharing.platform.security.AccountType;
import ru.gontarenko.code.sharing.platform.service.SnippetService;
import ru.gontarenko.code.sharing.platform.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final SnippetService snippetService;

    @Autowired
    public ProfileController(UserService userService, SnippetService snippetService) {
        this.userService = userService;
        this.snippetService = snippetService;
    }

    @GetMapping(value = {"/{id}", ""})
    public String getProfile(
            @PathVariable("id") Optional<Long> optionalId,
            Model model,
            @AuthenticationPrincipal User user
    ) {
        Long id = optionalId.orElse(user.getId());
        User userProfile = user;
        List<CodeSnippet> snippetList;
        boolean isAuthenticationUserProfile = id.equals(user.getId());
        if (isAuthenticationUserProfile) {
            snippetList = snippetService.findAllByUser(userProfile);
        } else {
            userProfile = userService.findById(id);
            snippetList = snippetService.findPublicSnippetsByUser(userProfile);
        }

        model.addAttribute("user", userProfile);
        model.addAttribute("favLangs", userProfile.getProgrammingLanguages());
        model.addAttribute("myAcc", isAuthenticationUserProfile);
        model.addAttribute("isFreeAcc", user.getAccountType().equals(AccountType.FREE));
        model.addAttribute("snippetList", snippetList);
        model.addAttribute("canDelete", isAuthenticationUserProfile);

        return "profile/profile";
    }

    @GetMapping("/edit")
    public String getProfileToUpdate(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "profile/profile_edit";
    }

    @PatchMapping("/edit")
    public String updateProfile(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/profile_edit";
        }
        try {
            userService.update(user);
        } catch (Exception e) {
            bindingResult.addError(new FieldError("userProfile", "nickname", e.getMessage()));
            return "profile/profile_edit";
        }

        // refresh AuthenticationPrincipal
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/profile";
    }

    @PatchMapping("/buy-pro")
    public String buyPro(@AuthenticationPrincipal User user) {
        userService.buyPro(user);
        return "redirect:/profile";
    }
}