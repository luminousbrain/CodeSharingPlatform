package ru.gontarenko.codesharingplatform.service;

import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.exception.MyUserException;
import ru.gontarenko.codesharingplatform.secutrity.AccountStatus;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    void update(User user);

    void changeUserStatus(Long userId, AccountStatus accountStatus);

    void buyPro(User user);

    void save(User user) throws MyUserException;
}
