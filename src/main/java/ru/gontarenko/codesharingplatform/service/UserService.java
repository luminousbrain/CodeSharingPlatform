package ru.gontarenko.codesharingplatform.service;

import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.exception.MyUserException;

public interface UserService {
    void save(User user) throws MyUserException;
}
