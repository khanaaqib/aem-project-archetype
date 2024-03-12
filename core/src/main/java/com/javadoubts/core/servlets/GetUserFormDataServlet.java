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
import java.util.*;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/fetchUser/FetchUserServlet"
)
public class GetUserFormDataServlet extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String nodeLocation = "/content/practice/us/user-data";
        ResourceResolver resourceResolver = request.getResourceResolver();
        Iterator<Resource> itr = resourceResolver.getResource(nodeLocation).listChildren();
        List<Map<String, String>> list = new ArrayList<>();
        while (itr.hasNext()){
            Map<String, String > map = new HashMap<>();
            Resource resource = itr.next();
            ValueMap valueMap = resource.adaptTo(ValueMap.class);
            String firstName = valueMap.get("firstName",String.class);
            String lastName = valueMap.get("lastName",String.class);
            String email = valueMap.get("email",String.class);
            map.put("firstName", firstName);
            map.put("lastName", lastName);
            map.put("email", email);
            list.add(map);
        }
        response.getWriter().write(list.toString());
    }
}
