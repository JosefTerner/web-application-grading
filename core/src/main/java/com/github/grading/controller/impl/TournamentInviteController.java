package com.github.grading.controller.impl;

import com.github.grading.controller.ITournamentInviteController;
import com.github.grading.entity.TournamentInvite;
import com.github.grading.service.ITournamentInviteService;

import java.util.List;

public class TournamentInviteController implements ITournamentInviteController {

    private final ITournamentInviteService service;

    public TournamentInviteController(ITournamentInviteService service) {
        this.service = service;
    }

    @Override
    public List<TournamentInvite> getAll(long userId) {
        return service.getAll();
    }

    @Override
    public TournamentInvite get(long inviteId) {
        return null;
    }
}
