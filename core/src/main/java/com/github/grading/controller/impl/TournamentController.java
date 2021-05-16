package com.github.grading.controller.impl;

import com.github.grading.controller.ITournamentController;
import com.github.grading.entity.Tournament;
import com.github.grading.service.ITournamentService;

import java.util.List;

public class TournamentController implements ITournamentController {

    private final ITournamentService tournamentService;

    public TournamentController(ITournamentService tournamentService) {
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
}
