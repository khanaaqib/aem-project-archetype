package com.javadoubts.core.servlets;

import com.javadoubts.core.services.DemoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/demoPathservelet/readydemopathservlet"
)
public class DemoPathServlet extends SlingAllMethodsServlet {


    @Reference
    DemoService osgiservice;

    public String message;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        message= osgiservice.getFileInfo();
        response.getWriter().write(message);
    }
}
