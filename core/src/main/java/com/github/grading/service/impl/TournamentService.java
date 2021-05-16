package com.github.grading.service.impl;

import com.github.grading.entity.Tournament;
import com.github.grading.repository.ITournamentRepository;
import com.github.grading.service.ITournamentService;

import java.util.List;

public class TournamentService implements ITournamentService {

    private final ITournamentRepository repository;

    public TournamentService(ITournamentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tournament> getAll() {
        return repository.findAll();
    }

    @Override
    public Tournament get(long id) {
        return repository.findOne(id);
    }
}
