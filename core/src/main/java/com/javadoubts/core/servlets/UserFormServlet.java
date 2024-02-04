package com.javadoubts.core.servlets;

import com.javadoubts.core.services.CustomFormService;
import com.javadoubts.core.services.FetchResourceService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,immediate = true)
@SlingServletResourceTypes(
        resourceTypes = "practice/components/page",
        methods = "POST"
)
public class UserFormServlet extends SlingAllMethodsServlet {

    private Logger logger = LoggerFactory.getLogger(UserFormServlet.class);

    @Reference
    CustomFormService customFormService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        response.getWriter().write("userData");
    }

}
