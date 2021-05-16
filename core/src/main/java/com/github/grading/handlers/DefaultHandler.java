package com.github.grading.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.grading.controller.IGameController;
import com.github.grading.controller.ITournamentController;
import com.github.grading.controller.ITournamentInviteController;
import com.github.grading.controller.IUserController;
import com.github.grading.dto.UserAuthorizationDto;
import com.github.grading.dto.UserRegistrationDto;
import com.github.grading.exceptions.BadRequest;
import com.github.grading.payload.ResponseEnvelope;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/*")
public class DefaultHandler extends AbstractServlet {

    private final IUserController userControllers;
    private final ITournamentInviteController tournamentInviteController;
    private final ITournamentController tournamentController;
    private final IGameController gameController;

    public DefaultHandler(IUserController userControllers, ITournamentInviteController tournamentInviteController, ITournamentController tournamentController, IGameController gameController) {
        this.userControllers = userControllers;
        this.tournamentInviteController = tournamentInviteController;
        this.tournamentController = tournamentController;
        this.gameController = gameController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String[] splitUrl = url.split("/");

        // GET		/user               (получить весь список пользователей)
        // GET		/user/{user_id}		(получить пользователя по его айди)
        // GET 	    /user/{user_id}/invite

        if (splitUrl[1].equals("user")) {
            if (splitUrl.length == 4 && splitUrl[3].equals("invite")) {
                long userId = Long.parseLong(splitUrl[2]);
                sendResponse(resp, tournamentInviteController.getAll(userId));
            } else if (splitUrl.length == 3) {
                long userId = Long.parseLong(splitUrl[2]);
                sendResponse(resp, userControllers.get(userId));
            } else {
                sendResponse(resp, userControllers.getAll());
            }
        }

        // GET 	/tournament 				(получить список турниров)
        // GET 	/tournament/{tournament_id} (получить турнир по его айди)
        // GET 	/tournament/{tournament_id}/game 			(получить все игры в турнире)
        // GET 	/tournament/{tournament_id}/game/{game_id}	(получить игру с турнира по ее айди)

        if (splitUrl[1].equals("tournament")) {
            if (splitUrl.length >= 4) {
                if (splitUrl.length == 5) {
                    long gameId = Long.parseLong(splitUrl[4]);
                    sendResponse(resp, gameController.get(gameId));
                }
                long tournamentId = Long.parseLong(splitUrl[2]);
                sendResponse(resp, gameController.getAll(tournamentId));
            }
            if (splitUrl.length == 3) {
                long tournamentId = Long.parseLong(splitUrl[2]);
                sendResponse(resp, tournamentController.get(tournamentId));
            } else {
                sendResponse(resp, tournamentController.getAll());
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("DO POST");

        String url = req.getRequestURI();

        if (url.endsWith("/auth")) {
            UserAuthorizationDto payload = parseRequestBody(req, UserAuthorizationDto.class);
            System.out.println("AUTH PAYLOAD");
            System.out.println(payload.toString());
            String result = this.userControllers.authorize(payload).orElseThrow(BadRequest::new);
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(new ResponseEnvelope(result, userControllers.getRole()));
            System.out.println(str + " " + "JSON TOKEN");
            sendResponse(resp, str);
        } else if (url.endsWith("/registration")) {
            UserRegistrationDto payload = parseRequestBody(req, UserRegistrationDto.class);
            System.out.println("OUT PAYLOAD");
            System.out.println(payload);
            this.userControllers.register(payload);
            System.out.println("AFTER SET PAYLOAD");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
