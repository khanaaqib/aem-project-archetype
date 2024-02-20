package com.javadoubts.core.servlets;

import com.javadoubts.core.services.UserRegistrationFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/user/UserServlet"
)
public class UserProfileServlet extends SlingAllMethodsServlet {

    @Reference
    UserRegistrationFormService userRegistrationFormService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String userInfo = request.getParameter("userProfile");
        List responseValue  = userRegistrationFormService.getSpecificRecord(request,userInfo);
        response.getWriter().write(responseValue.toString());
    }
}
