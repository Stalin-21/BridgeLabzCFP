package com.stalin.day1.uc4.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Create or get existing session
        HttpSession session = request.getSession();

        // Store data in session
        session.setAttribute("username", "stalin");

        // Forward to JSP
        request.getRequestDispatcher("session.jsp")
                .forward(request, response);
    }
}
