package com.javadoubts.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/kds/kdsServlet"
)
public class BasicCustomServlet extends SlingAllMethodsServlet {

    private Logger log = LoggerFactory.getLogger(BasicCustomServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);
            String title=null;
        try {
            Node node = session.getNode("/content/practice/us/en/testcomponent/jcr:content");
            //title = String.valueOf(node.getProperty("jcr:title"));
            Resource resource = resourceResolver.getResource("/content/practice/us/en/testcomponent/jcr:content");
            ValueMap valueMap = resource.adaptTo(ValueMap.class);
            log.info("value map check:{}",valueMap);
            title = valueMap.get("jcr:title",String.class);
            log.info("title value check{}",title);
            node.setProperty("firstName","Aaqib");
            node.setProperty("last","Khan");
            session.save();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(title);
    }
}
