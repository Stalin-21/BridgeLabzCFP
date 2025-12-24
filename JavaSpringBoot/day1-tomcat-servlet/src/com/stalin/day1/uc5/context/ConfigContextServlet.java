package com.stalin.day1.uc5.context;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigContextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // ServletConfig (servlet-level)
        ServletConfig config = getServletConfig();
        String servletName = config.getInitParameter("servletName");

        // ServletContext (application-level)
        ServletContext context = getServletContext();
        String appName = context.getInitParameter("applicationName");

        out.println("<h2>ServletConfig & ServletContext Example</h2>");
        out.println("<p>Servlet Name (ServletConfig): <b>" + servletName + "</b></p>");
        out.println("<p>Application Name (ServletContext): <b>" + appName + "</b></p>");
    }
}
