package ru.gontarenko.codesharingplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gontarenko.codesharingplatform.enitity.User;
import ru.gontarenko.codesharingplatform.exception.MyUserException;
import ru.gontarenko.codesharingplatform.repository.UserRepository;
import ru.gontarenko.codesharingplatform.secutrity.AccountStatus;
import ru.gontarenko.codesharingplatform.secutrity.AccountType;
import ru.gontarenko.codesharingplatform.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public final class UserServiceImpl implements UserService {
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

    @Override
    public List<User> findAll() {
        return userRepo.findAllByAccountTypeIsNotOrderById(AccountType.ADMIN);
    }

    @Override
    public void changeUserStatus(Long userId, AccountStatus accountStatus) {
        Optional<User> byId = userRepo.findById(userId);
        if (byId.isEmpty()) {
            return;
        }
        User user = byId.get();
        user.setAccountStatus(accountStatus);
        userRepo.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new MyUserException("User not found")
        );
    }

    @Override
    public void update(User user) {
        try {
            userRepo.save(user);
        } catch (Exception e) {
            throw new MyUserException("Nickname already taken");
        }
    }

    @Override
    public void buyPro(User user) {
        user.setAccountType(AccountType.PRO);
        userRepo.save(user);
    }
}
