package com.javadoubts.core.servlets;

import com.javadoubts.core.services.RegistrationFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/kds/getuserdata"
)
public class FetchUserRecordServlet extends SlingAllMethodsServlet {

    @Reference
    RegistrationFormService registrationFormService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String userData = request.getParameter("userData");
        List<Map<String,String>> userRecord = new ArrayList<>();
        try {
            userRecord = registrationFormService.getUserRecord(userData);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(userRecord.toString());
    }
}
