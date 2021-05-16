package com.github.grading.controller.impl;

import com.github.grading.controller.IUserController;
import com.github.grading.dto.UserAuthorizationDto;
import com.github.grading.dto.UserRegistrationDto;
import com.github.grading.payload.Token;
import com.github.grading.repository.IUserRepository;
import com.github.grading.utils.TokenProvider;
import com.github.grading.utils.TransferObj;

import java.util.Optional;

public class UserController implements IUserController {

    private final IUserRepository userRepository;

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

            return Optional.ofNullable(TokenProvider.encode(token));
        });
    }

    @Override
    public void register(UserRegistrationDto userRegDto) {
        this.userRepository.save(TransferObj.toUser(userRegDto));
    }
}

