package com.github.grading.config;

import com.github.grading.controller.*;
import com.github.grading.controller.impl.*;

public class ControllerConfig {

    private static final IAdminGameController adminGameController = new AdminGameController();
    private static final IAdminTournamentController adminTournamentController = new AdminTournamentController(ServiceConfig.getTournamentService());
    private static final IGameController gameController = new GameController(ServiceConfig.getGameService());
    private static final ITournamentController tournamentController = new TournamentController(ServiceConfig.getTournamentService());
    private static final IUserController userController = new UserController(RepositoryConfig.getUserRepository());

    private static final ITournamentInviteController tournamentInviteController = new TournamentInviteController(ServiceConfig.getTournamentInviteService());

    private static final IAdminTournamentInviteController adminTournamentInviteController = new AdminTournamentInviteController(ServiceConfig.getTournamentInviteService());
    public static IAdminGameController getAdminGameController() {
        return adminGameController;
    }

    public static IAdminTournamentController getAdminTournamentController() {
        return adminTournamentController;
    }

    public static IGameController getGameController() {
        return gameController;
    }

    public static ITournamentController getTournamentController() {
        return tournamentController;
    }

    public static IUserController getUserController() {
        return userController;
    }

    public static ITournamentInviteController getTournamentInviteController() {
        return tournamentInviteController;
    }

    public static IAdminTournamentInviteController getAdminTournamentInviteController() {
        return adminTournamentInviteController;
    }
}
