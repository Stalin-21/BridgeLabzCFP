package com.stalin.day1.uc2.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Predefined credentials (as per Day-1 document)
    private static final String USERNAME = "narayan";
    private static final String PASSWORD = "bridgelabz";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            // Forward to JSP on success
            request.setAttribute("user", username);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("loginSuccess.jsp");
            dispatcher.forward(request, response);

        } else {
            // Login failure response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<h2>Login Failed</h2>");
            out.println("<p>Invalid username or password</p>");
            out.println("<a href='login.html'>Try Again</a>");
        }
    }
}

