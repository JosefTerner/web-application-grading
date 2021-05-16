package com.github.grading.controller;

import com.github.grading.entity.TournamentInvite;

import java.util.List;

public interface ITournamentInviteController {

    List<TournamentInvite> getAll(long userId);

    TournamentInvite get(long inviteId);
}
