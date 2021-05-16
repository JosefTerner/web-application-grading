package com.github.grading.service.impl;

import com.github.grading.entity.TournamentInvite;
import com.github.grading.repository.ITournamentInviteRepository;
import com.github.grading.service.ITournamentInviteService;

import java.util.List;

public class TournamentInviteService implements ITournamentInviteService {

    private final ITournamentInviteRepository repository;

    public TournamentInviteService(ITournamentInviteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TournamentInvite> getAll() {
        return repository.findAll();
    }
}
