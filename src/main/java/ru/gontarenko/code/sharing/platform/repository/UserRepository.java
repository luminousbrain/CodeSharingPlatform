package ru.gontarenko.code.sharing.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontarenko.code.sharing.platform.enitity.User;
import ru.gontarenko.code.sharing.platform.security.AccountType;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByAccountTypeIsNotOrderById(AccountType accountType);
}
