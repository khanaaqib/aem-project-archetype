package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/custom/customPostServlet"
)
public class FirstAEMPostServlet extends SlingAllMethodsServlet {

    private Logger log = LoggerFactory.getLogger(FirstAEMPostServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
         String firstName = request.getParameter("firstname");
         String lastName = request.getParameter("lastname");
         log.info("fistName Value Check:::{}, last Name value check:::{}",firstName,lastName);
         response.getWriter().write("form submitted successfull");
    }
}
