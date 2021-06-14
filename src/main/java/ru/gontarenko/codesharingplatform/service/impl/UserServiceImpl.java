package ru.gontarenko.codesharingplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.exception.MyUserException;
import ru.gontarenko.codesharingplatform.repository.UserRepository;
import ru.gontarenko.codesharingplatform.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) throws MyUserException {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new MyUserException("Email already taken");
        }
        user.setDefaultSettingsForNewUser(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
