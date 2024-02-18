package com.javadoubts.core.servlets;

import com.javadoubts.core.services.UserRegistrationFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/user/userInfo/userRegistrationForm"
)
public class UserRegistrationFormServlet extends SlingAllMethodsServlet {

    @Reference
    UserRegistrationFormService userService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String userResponse = null;
        try {
            userResponse = userService.addUserDataToNode(request);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(userResponse);
    }
}
