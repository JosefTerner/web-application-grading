package com.github.grading.repository;

import com.github.grading.entity.User;

import java.util.Optional;

public interface IUserRepository extends Repository<User, Long> {

    Optional<User> findByLogin(String login);

    void updatePassword(long id, String password);

    void updateLogin(long id, String login);
}
