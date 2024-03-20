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
        value="/bin/fetch/fetchuserdataservlet"
)
public class FetchUserDataServlet extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        String nodeLocation ="/content/practice/aem-simple-form-Info";
        Iterator<Resource> itr = resourceResolver.getResource(nodeLocation).listChildren();
        List<Map<String,String>> list = new ArrayList<>();
        while (itr.hasNext()) {
            Resource resource = itr.next();
            ValueMap valueMap = resource.adaptTo(ValueMap.class);
            Map<String, String> map = new HashMap<>();
            map.put("firstName", valueMap.get("firstName", String.class));
            map.put("lastName", valueMap.get("lastName", String.class));
            map.put("EmailAddress", valueMap.get("email", String.class));
            map.put("Address", valueMap.get("address", String.class));
            list.add(map);
        }
        response.getWriter().write(list.toString());
    }
}
