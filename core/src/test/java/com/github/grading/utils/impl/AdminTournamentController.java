package com.github.grading.utils.impl;

import com.github.grading.controller.IAdminTournamentController;
import com.github.grading.dto.CreateTournamentDto;
import com.github.grading.dto.UpdateTournamentDto;
import com.github.grading.entity.Tournament;
import com.github.grading.service.ITournamentService;

import java.util.List;

public class AdminTournamentController implements IAdminTournamentController {

    private final ITournamentService tournamentService;

    public AdminTournamentController(ITournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Override
    public List<Tournament> getAll() {
        return tournamentService.getAll();
    }

    @Override
    public Tournament get(long id) {
        return tournamentService.get(id);
    }

    @Override
    public void create(CreateTournamentDto tournament) {
        tournamentService.save(tournament);
    }

    @Override
    public void update(long id, UpdateTournamentDto tournament) {
        tournamentService.update(id, tournament);
    }

    @Override
    public void delete(long id) {
        tournamentService.delete(id);
    }
}
