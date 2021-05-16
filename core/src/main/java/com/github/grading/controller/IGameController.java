package com.github.grading.controller;

import com.github.grading.entity.Game;

import java.util.List;

public interface IGameController {

    List<Game> getAll();

    List<Game> getAll(long tournamentId);

    Game get(long id);
}
