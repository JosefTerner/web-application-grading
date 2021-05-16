package com.github.grading.repository;

import com.github.grading.entity.Game;

import java.util.List;

public interface IGameRepository extends Repository<Game, Long> {

    List<Game> findAll(long tournamentId);
}