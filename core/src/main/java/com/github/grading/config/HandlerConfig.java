package com.github.grading.config;

import com.github.grading.handlers.*;

public class HandlerConfig {

    private static final AdminHandler ADMIN_HANDLER = new AdminHandler(ControllerConfig.getAdminTournamentController());
    private static final DefaultHandler DEFAULT_HANDLER = new DefaultHandler(
            ControllerConfig.getUserController(),
            ControllerConfig.getTournamentInviteController(),
            ControllerConfig.getTournamentController(),
            ControllerConfig.getGameController());

    public static DefaultHandler getDefaultHandler() {
        return DEFAULT_HANDLER;
    }

    public static AdminHandler getAdminHandler() {
        return ADMIN_HANDLER;
    }
}
