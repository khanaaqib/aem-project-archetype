package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths(
        value="/bin/user/userSpecificServlet"
)
public class UserSpecificServelt extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        String firstName = request.getParameter("firstName");
        String nodeLocation = "/content/practice/us/user-data/" + firstName;
        Resource resource = resourceResolver.getResource(nodeLocation);
        ValueMap valueMap = resource.adaptTo(ValueMap.class);
        Map<String, String> map= new HashMap<>();
        String fName = valueMap.get("firstName",String.class);
        String lName = valueMap.get("lastName",String.class);
        String email = valueMap.get("email",String.class);
        map.put("firstName",fName);
        map.put("lastName",lName);
        map.put("email",email);
        response.getWriter().write(map.toString());


    }
}
