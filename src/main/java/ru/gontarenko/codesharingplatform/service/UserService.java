package ru.gontarenko.codesharingplatform.service;

import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.exception.MyUserException;
import ru.gontarenko.codesharingplatform.secutrity.AccountStatus;

import java.util.List;

public interface UserService {
    void save(User user) throws MyUserException;

    List<User> findAll();

    void changeUserStatus(Long userId, AccountStatus accountStatus);
}
