package com.github.grading.handlers;

import com.github.grading.controller.IAdminTournamentController;
import com.github.grading.dto.CreateTournamentDto;
import com.github.grading.dto.UserAuthorizationDto;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String[] splitUrl = url.split("/");

        // POST 	/admin/tournament 		(создать новый турнир)

        if (splitUrl[2].equals("tournament")) {
            CreateTournamentDto createTournamentDto = parseRequestBody(req, CreateTournamentDto.class);
            controller.create(createTournamentDto);
        }
    }
}
