package com.github.grading.controller.impl;

import com.github.grading.controller.IAdminTournamentInviteController;
import com.github.grading.dto.CreateTournamentInviteDto;
import com.github.grading.dto.UpdateTournamentInviteDto;
import com.github.grading.entity.TournamentInvite;
import com.github.grading.service.ITournamentInviteService;

import java.util.List;

public class AdminTournamentInviteController implements IAdminTournamentInviteController {

    private final ITournamentInviteService service;

    public AdminTournamentInviteController(ITournamentInviteService service) {
        this.service = service;
    }

    @Override
    public List<TournamentInvite> getAll() {
        return service.getAll();
    }

    @Override
    public TournamentInvite get(long id) {
        return null;
    }

    @Override
    public long create(CreateTournamentInviteDto tournamentInvite) {
        return 0;
    }

    @Override
    public void update(long id, UpdateTournamentInviteDto tournamentInvite) {

    }

    @Override
    public void delete(long id) {

    }
}
