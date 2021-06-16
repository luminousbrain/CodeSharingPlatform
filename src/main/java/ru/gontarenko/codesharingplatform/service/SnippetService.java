package ru.gontarenko.codesharingplatform.service;

import org.springframework.validation.BindingResult;
import ru.gontarenko.codesharingplatform.enitity.CodeSnippet;
import ru.gontarenko.codesharingplatform.enitity.User;

import java.util.List;

public interface SnippetService {
    List<CodeSnippet> findAll();

    CodeSnippet findByLink(String link);

    void save(CodeSnippet snippet, BindingResult bindingResult);

    void delete(String link, User user);

    List<CodeSnippet> findPublicSnippetsByUser(User user);

    List<CodeSnippet> findAllByUser(User user);
}
