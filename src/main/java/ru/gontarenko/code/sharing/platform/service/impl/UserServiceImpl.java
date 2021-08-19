package ru.gontarenko.code.sharing.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gontarenko.code.sharing.platform.service.UserService;
import ru.gontarenko.code.sharing.platform.enitity.User;
import ru.gontarenko.code.sharing.platform.exception.UserException;
import ru.gontarenko.code.sharing.platform.repository.UserRepository;
import ru.gontarenko.code.sharing.platform.security.AccountStatus;
import ru.gontarenko.code.sharing.platform.security.AccountType;

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
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new UserException("User not found")
        );
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAllByAccountTypeIsNotOrderById(AccountType.ADMIN);
    }

    @Override
    public void update(User user) {
        try {
            userRepo.save(user);
        } catch (Exception e) {
            throw new UserException("Nickname already taken");
        }
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
    public void buyPro(User user) {
        user.setAccountType(AccountType.PRO);
        userRepo.save(user);
    }

    @Override
    public void save(User user) throws UserException {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new UserException("Email already taken");
        }
        user.setDefaultSettingsForNewUser(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
