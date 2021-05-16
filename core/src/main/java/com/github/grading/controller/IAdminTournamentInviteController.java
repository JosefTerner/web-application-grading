package com.github.grading.controller;

import com.github.grading.dto.CreateTournamentInviteDto;
import com.github.grading.dto.UpdateTournamentInviteDto;
import com.github.grading.entity.TournamentInvite;

import java.util.List;

public interface IAdminTournamentInviteController {

    List<TournamentInvite> getAll();

    TournamentInvite get(long id);

    long create(CreateTournamentInviteDto tournamentInvite);

    void update(long id, UpdateTournamentInviteDto tournamentInvite);

    void delete(long id);
}
