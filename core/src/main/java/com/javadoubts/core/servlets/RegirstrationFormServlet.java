package com.javadoubts.core.servlets;

import com.javadoubts.core.services.AEMCustomFormService;
import com.javadoubts.core.services.AEMSimpleFormService;
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
        value = "/bin/user/userForm"
)
public class RegirstrationFormServlet extends SlingAllMethodsServlet {

    @Reference
    AEMSimpleFormService aemSimpleFormService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
         String responseCheck=aemSimpleFormService.addFormDataToNode(request);
         response.getWriter().write(responseCheck);
    }
}
