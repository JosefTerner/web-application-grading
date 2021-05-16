package com.github.grading.handlers;

import com.github.grading.controller.IAdminTournamentController;
import com.github.grading.dto.CreateTournamentDto;
import com.github.grading.dto.UpdateTournamentDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminHandler extends AbstractServlet {

    private final IAdminTournamentController controller;

    public AdminHandler(IAdminTournamentController controller) {
        this.controller = controller;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendResponse(resp, controller.getAll());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String[] splitUrl = url.split("/");

        // PUT 	/admin/tournament/{tournament_id}	(редактировать турнир по его айди)

        if (splitUrl[2].equals("tournament")) {
            long tournamentId = Long.parseLong(splitUrl[3]);
            UpdateTournamentDto updateTournamentDto = parseRequestBody(req, UpdateTournamentDto.class);
            controller.update(tournamentId, updateTournamentDto);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String[] splitUrl = url.split("/");

        // POST 	/admin/tournament 		(создать новый турнир)

        if (splitUrl[2].equals("tournament")) {
            CreateTournamentDto createTournamentDto = parseRequestBody(req, CreateTournamentDto.class);
            controller.create(createTournamentDto);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String[] splitUrl = url.split("/");

        // DELETE 	/admin/tournament/{tournament_id}	(удалить турнир по его айди)

        if (splitUrl[2].equals("tournament")) {
            long tournamentId = Long.parseLong(splitUrl[3]);
            controller.delete(tournamentId);
        }
    }
}
