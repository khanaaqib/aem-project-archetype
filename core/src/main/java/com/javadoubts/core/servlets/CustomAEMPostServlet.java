package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/aem/postDemoServlet"
)
public class CustomAEMPostServlet extends SlingAllMethodsServlet {
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String firstname= request.getParameter("firstName");
        String lastName= request.getParameter("lastName");
        String email= request.getParameter("email");
        String contactNo= request.getParameter("contactNo");
        String address= request.getParameter("address");
        String zipcode= request.getParameter("zipcode");
        String Comment= request.getParameter("Comment");
        response.getWriter().write(firstname + lastName + email + contactNo);
    }
}
