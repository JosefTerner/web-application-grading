package com.github.grading.utils.impl;

import com.github.grading.controller.IAdminGameController;
import com.github.grading.controller.IGameController;
import com.github.grading.dto.CreateGameDto;
import com.github.grading.dto.UpdateGameDto;
import com.github.grading.entity.Game;
import com.github.grading.repository.IUserRepository;

import java.util.List;

public class AdminGameController implements IAdminGameController {

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public Game get(long id) {
        return null;
    }

    @Override
    public long create(CreateGameDto tournament) {
        return 0;
    }

    @Override
    public void update(long id, UpdateGameDto tournament) {

    }

    @Override
    public void delete(long id) {

    }
}
