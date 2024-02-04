package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/demo/demopathservlet"
)
public class DemoPathPostServlet extends SlingAllMethodsServlet {
    private Logger log = LoggerFactory.getLogger(DemoPathPostServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        Node  node = null;
        try {
             node = session.getNode("/content/practice/us/en/aempage/last-name");
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        try {
            node.setProperty("firstName",firstName);
            node.setProperty("last",lastName);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        log.info("first name check{}",firstName);
        log.info("last name check{}",lastName);
        response.getWriter().write("firsName"+ firstName +""+"latName" +lastName);
    }
}
