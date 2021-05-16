package com.github.grading.controller;

import com.github.grading.dto.UserAuthorizationDto;
import com.github.grading.dto.UserRegistrationDto;
import com.github.grading.entity.TournamentInvite;
import com.github.grading.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserController {

    Optional<String> authorize(UserAuthorizationDto userAuthDto);

    void register(UserRegistrationDto userRegDto);

    String getRole();

    List<User> getAll();

    User get(long userId);
}
