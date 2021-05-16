package com.github.grading.service.impl;

import com.github.grading.entity.Game;
import com.github.grading.repository.IGameRepository;
import com.github.grading.service.IGameService;

import java.util.List;

public class GameService implements IGameService {

    private final IGameRepository repository;

    public GameService(IGameRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Game> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Game> getAll(long tournamentId) {
        return repository.findAll(tournamentId);
    }

    @Override
    public Game get(long gameId) {
        return repository.findOne(gameId);
    }
}
