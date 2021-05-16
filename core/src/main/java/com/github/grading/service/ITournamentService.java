package com.github.grading.service;

import com.github.grading.dto.CreateTournamentDto;
import com.github.grading.dto.UpdateTournamentDto;
import com.github.grading.entity.Tournament;

import java.util.List;

public interface ITournamentService {

    List<Tournament> getAll();

    Tournament get(long id);

    void save(CreateTournamentDto tournament);

    void update(long id, UpdateTournamentDto tournament);

    void delete(long id);
}
