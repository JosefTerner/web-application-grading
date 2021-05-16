package com.github.grading.utils.impl;

import com.github.grading.controller.IGameController;
import com.github.grading.entity.Game;
import com.github.grading.service.IGameService;

import java.util.List;

public class GameController implements IGameController {

    private final IGameService service;

    public GameController(IGameService service) {
        this.service = service;
    }

    @Override
    public List<Game> getAll() {
        return service.getAll();
    }

    @Override
    public List<Game> getAll(long tournamentId) {
        return service.getAll(tournamentId);
    }

    @Override
    public Game get(long id) {
        return service.get(id);
    }
}
