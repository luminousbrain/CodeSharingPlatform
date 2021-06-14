package ru.gontarenko.codesharingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.secutrity.AccountType;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByAccountTypeIsNotOrderById(AccountType accountType);
}
