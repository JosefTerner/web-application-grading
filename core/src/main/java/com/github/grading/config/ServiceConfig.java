package com.github.grading.config;

import com.github.grading.service.IGameService;
import com.github.grading.service.ITournamentInviteService;
import com.github.grading.service.ITournamentService;
import com.github.grading.service.impl.GameService;
import com.github.grading.service.impl.TournamentInviteService;
import com.github.grading.service.impl.TournamentService;

public class ServiceConfig {

    private static final ITournamentService tournamentService = new TournamentService(RepositoryConfig.getTournamentRepository());
    private static final ITournamentInviteService tournamentInviteService = new TournamentInviteService(RepositoryConfig.getTournamentInviteRepository());
    private static final IGameService gameService = new GameService(RepositoryConfig.getGameRepository());

    public static ITournamentService getTournamentService() {
        return tournamentService;
    }

    public static ITournamentInviteService getTournamentInviteService() {
        return tournamentInviteService;
    }

    public static IGameService getGameService() {
        return gameService;
    }
}
