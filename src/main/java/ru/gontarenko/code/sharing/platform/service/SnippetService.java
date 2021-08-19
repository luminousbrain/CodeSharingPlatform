package ru.gontarenko.code.sharing.platform.service;

import org.springframework.validation.BindingResult;
import ru.gontarenko.code.sharing.platform.enitity.CodeSnippet;
import ru.gontarenko.code.sharing.platform.enitity.User;

import java.util.List;

public interface SnippetService {
    CodeSnippet findByLink(String link);

    List<CodeSnippet> findAll();

    List<CodeSnippet> findPublicSnippetsByUser(User user);

    List<CodeSnippet> findAllByUser(User user);

    void save(CodeSnippet snippet, BindingResult bindingResult);

    void delete(String link, User user);
}
