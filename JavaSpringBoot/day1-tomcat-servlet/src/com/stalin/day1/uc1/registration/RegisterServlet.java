package com.stalin.day1.uc1.registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        if (name == null || email == null || password == null ||
                name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            out.println("<h2 style='color:red'>All fields are required</h2>");

        } else {
            out.println("<h2>User Registration Successful</h2>");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Email: " + email + "</p>");
        }

        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.html");
    }
}
