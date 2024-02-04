package com.javadoubts.core.servlets;

import com.javadoubts.core.services.FirstService;
import com.javadoubts.core.services.TestAEMService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "practice/components/page"
)
public class TestAEMServlet extends SlingAllMethodsServlet {

    private Logger log = LoggerFactory.getLogger(TestAEMServlet.class);

    @Reference
    TestAEMService testService;

    @Reference
    FirstService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        log.info("firstName Value Check {}",firstName);
        String fullName = testService.getUserInfo();
        log.info("fullName value check ::{},{}",fullName,firstName);
        String message = service.getFileInput();
        log.info("file value check::{}",message);
        log.info("Servlet Successfully trigger");
         response.getWriter().write("Success message");

    }
}
