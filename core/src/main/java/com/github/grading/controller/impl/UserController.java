package com.github.grading.controller.impl;

import com.github.grading.controller.IUserController;
import com.github.grading.dto.UserAuthorizationDto;
import com.github.grading.dto.UserChangeLoginDto;
import com.github.grading.dto.UserChangePasswordDto;
import com.github.grading.dto.UserRegistrationDto;
import com.github.grading.entity.User;
import com.github.grading.exceptions.BadRequest;
import com.github.grading.payload.Token;
import com.github.grading.repository.IUserRepository;
import com.github.grading.utils.TokenProvider;
import com.github.grading.utils.TransferObj;

import java.util.List;
import java.util.Optional;

public class UserController implements IUserController {

    private final IUserRepository userRepository;

    private String role;

    public String getRole() {
        return role;
    }

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> authorize(UserAuthorizationDto userAuthDto) {
        return userRepository.findByLogin(userAuthDto.getLogin()).flatMap(user -> {
            Token token = new Token(
                    user.getLogin(),
                    System.currentTimeMillis(),
                    System.currentTimeMillis() + 18000
            );
            this.role = user.getRole();
            return Optional.ofNullable(TokenProvider.encode(token));
        });
    }

    @Override
    public void register(UserRegistrationDto userRegDto) {
        this.userRepository.save(TransferObj.toUser(userRegDto));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public void changeLogin(long userId, UserChangeLoginDto userChangeLoginDto) {
        User user = get(userId);

        if (user == null) {
            throw new BadRequest("User with this id can not be found!");
        }

        if (user.getPassword().equals(userChangeLoginDto.getPassword())) {

            boolean userWithNewLoginExist = getAll().stream()
                    .anyMatch(u -> userChangeLoginDto.getNewLogin().equals(u.getEmail()));

            if (userWithNewLoginExist) {
                throw new BadRequest("User with this login already exist!");
            }

            user.setLogin(userChangeLoginDto.getNewLogin());
            userRepository.update(user);
        } else {
            throw new BadRequest("Passwords are not equal!");
        }
    }

    @Override
    public void changePassword(long userId, UserChangePasswordDto userChangePasswordDto) {
        User user = get(userId);

        if (user == null) {
            throw new BadRequest("User with this id can not be found!");
        }

        if (user.getPassword().equals(userChangePasswordDto.getOldPassword())) {
            user.setPassword(userChangePasswordDto.getNewPassword());
            userRepository.update(user);
        } else {
            throw new BadRequest("Passwords are not equal!");
        }
    }
}

