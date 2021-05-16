package com.github.grading.controller;

import com.github.grading.dto.CreateTournamentDto;
import com.github.grading.dto.UpdateTournamentDto;
import com.github.grading.entity.Tournament;

import java.util.List;

public interface IAdminTournamentController {

    List<Tournament> getAll();

    Tournament get(long id);

    long create(CreateTournamentDto tournament);

    void update(long id, UpdateTournamentDto tournament);

    void delete(long id);
}
