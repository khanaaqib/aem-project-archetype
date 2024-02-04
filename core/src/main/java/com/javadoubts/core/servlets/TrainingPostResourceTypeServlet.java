package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "practice/components/page",
        methods = "POST"
)
public class TrainingPostResourceTypeServlet extends SlingAllMethodsServlet {

    private Logger log = LoggerFactory.getLogger(TrainingPostResourceTypeServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        log.info("first name check{}",firstName);
        log.info("last name check{}",lastName);
        response.getWriter().write("firstname" + " " + firstName +"  "+ "lastName" + " " +lastName +"  "+ "email"+ " " + email);
    }
}
