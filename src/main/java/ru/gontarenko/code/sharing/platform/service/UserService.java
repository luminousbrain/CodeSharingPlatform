package ru.gontarenko.code.sharing.platform.service;

import ru.gontarenko.code.sharing.platform.enitity.User;
import ru.gontarenko.code.sharing.platform.exception.UserException;
import ru.gontarenko.code.sharing.platform.security.AccountStatus;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    void update(User user);

    void changeUserStatus(Long userId, AccountStatus accountStatus);

    void buyPro(User user);

    void save(User user) throws UserException;
}
