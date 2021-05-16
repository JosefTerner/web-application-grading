package com.github.grading.config;

import com.github.grading.repository.*;
import com.github.grading.repository.impl.*;
import lombok.Getter;

@Getter
public class RepositoryConfig {

    private static final IUserRepository userRepository = new UserRepository();
    private static final IPlayerRepository playerRepository = new PlayerRepository();
    private static final ITournamentRepository tournamentRepository = new TournamentRepository();
    private static final ITournamentInviteRepository tournamentInviteRepository = new TournamentInviteRepository();
    private static final IGameRepository gameRepository = new GameRepository();

    public static IUserRepository getUserRepository() {
        return userRepository;
    }

    public static IPlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    public static ITournamentRepository getTournamentRepository() {
        return tournamentRepository;
    }

    public static IGameRepository getGameRepository() {
        return gameRepository;
    }

    public static ITournamentInviteRepository getTournamentInviteRepository() {
        return tournamentInviteRepository;
    }
}
