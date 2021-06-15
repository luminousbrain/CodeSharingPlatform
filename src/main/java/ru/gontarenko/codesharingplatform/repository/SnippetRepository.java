package ru.gontarenko.codesharingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontarenko.codesharingplatform.enitity.CodeSnippet;

import java.util.List;
import java.util.Optional;

public interface SnippetRepository extends JpaRepository<CodeSnippet, Long> {
    Optional<CodeSnippet> findByLink(String link);

    boolean existsByLink(String link);

    List<CodeSnippet> findAllByHiddenOrderByDateCreateDesc(boolean hidden);
}
