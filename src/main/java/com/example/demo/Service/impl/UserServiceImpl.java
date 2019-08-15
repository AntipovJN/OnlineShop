package com.example.demo.Service.impl;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void addUser(String email, String password, String passwordAgain, String role)
            throws IllegalArgumentException, LoginException {
        validateUserData(email, password, passwordAgain);
        if ((getByEmail(email).isPresent())) {
            throw new LoginException("Try another login");
        }
        password = bCryptPasswordEncoder.encode(password);
        userRepository.save(new User(email, password, role));
    }

    @Transactional
    @Override
    public void updateUser(Long id, String newEmail, String newPassword, String newPasswordAgain)
            throws IllegalArgumentException, LoginException {
        validateUserData(newEmail, newPassword, newPasswordAgain);
        Optional<User> optionalUser = userRepository.findByEmail(newEmail);
        if (optionalUser.isPresent()) {
            if (!optionalUser.get().getId().equals(id)) {
                throw new LoginException("Use another email");
            }
        }
        optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            newPassword = bCryptPasswordEncoder.encode(newPassword);
            User user = optionalUser.get();
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        Optional<User> optionalUser =userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(userRepository.findById(id).get());
        } else{}
//            log.error(String.format("Failed removing user with id ='%s'", id));
    }

    @Transactional
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    private void validateUserData(String email, String password, String passwordAgain)
            throws IllegalArgumentException {
        if (Objects.isNull(email) || email.isEmpty()) {
            throw new IllegalArgumentException("You must use email for registration");
        }
        if (Objects.isNull(password) || Objects.isNull(passwordAgain)
                || password.isEmpty() || passwordAgain.isEmpty()) {
            throw new IllegalArgumentException("You must use password for registration");
        }
        if (!password.equals(passwordAgain)) {
            throw new IllegalArgumentException("Passwords not equals");
        }
    }
}
