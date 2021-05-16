package com.github.grading.service;

import com.github.grading.entity.Game;

import java.util.List;

public interface IGameService {

    List<Game> getAll();

    List<Game> getAll(long tournamentId);

    Game get(long gameId);
}
