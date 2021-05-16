package com.github.grading.service;

import com.github.grading.entity.Tournament;

import java.util.List;

public interface ITournamentService {

    List<Tournament> getAll();

    Tournament get(long id);
}
