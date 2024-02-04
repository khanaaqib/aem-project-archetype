package com.javadoubts.core.servlets;

import com.javadoubts.core.services.TrainingJanService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/kds/trainingjanServlet"
)
public class TrainingJanServlet extends SlingAllMethodsServlet {

    @Reference
    TrainingJanService config;

    private String user;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        user= config.getuserInfo();
        response.getWriter().write(user);
    }
}
