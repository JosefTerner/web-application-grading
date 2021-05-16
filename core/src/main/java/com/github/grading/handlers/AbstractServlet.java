package com.github.grading.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.grading.exceptions.BadRequest;
import com.github.grading.exceptions.NotFound;
import com.github.grading.utils.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AbstractServlet extends HttpServlet {

    protected static <T> T parseRequestBody(HttpServletRequest req, Class<T> clazz) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining());
        return JsonHelper.fromJson(body, clazz).orElseThrow(BadRequest::new);
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SERVICE");
        try {
            if (req.getHeader("Content-Type") != null && !req.getHeader("Content-Type").contains("application/json")) {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type");
                return;
            }
            System.out.println("IN SERVICE");
            setDefaultHeaders(resp);
            super.service(req, resp);
        } catch (BadRequest e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid body.");
        } catch (NotFound e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found.");
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("DO OPTIONS");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setStatus(204);
    }

    protected void setDefaultHeaders(HttpServletResponse resp) {
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "*");
//        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    protected void sendResponse(HttpServletResponse resp, Object result) throws IOException {
        ServletOutputStream out = resp.getOutputStream();

        out.write(new ObjectMapper().writeValueAsString(result).getBytes());
        out.flush();
        out.close();
    }
}
