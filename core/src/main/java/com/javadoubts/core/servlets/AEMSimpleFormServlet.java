package com.javadoubts.core.servlets;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/post/customServlet"
)
public class AEMSimpleFormServlet extends SlingAllMethodsServlet {
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        String createdNode ="";
        String nodelocation = "/content/practice/aem-simple-form-Info";
        try {
            if(session.nodeExists(nodelocation)){
                createdNode=CreateUserSpecificNode(session,request,nodelocation);
            }else {
                createSimpleFormNode(session);
                createdNode=CreateUserSpecificNode(session,request,nodelocation);
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(createdNode);
    }

    public String CreateUserSpecificNode(Session session, SlingHttpServletRequest request, String nodelocation) throws RepositoryException {
        String email = request.getParameter("Email");
        Node simpleFormNode = session.getNode(nodelocation);
        if(simpleFormNode.hasNode(email)){
            return "user is already exist";
        } else{
            Node userSpecificNode = simpleFormNode.addNode(email,"nt:unstructured");
            userSpecificNode.setProperty("firstName", request.getParameter("firstName"));
            userSpecificNode.setProperty("lastName",request.getParameter("lastName"));
            userSpecificNode.setProperty("email",request.getParameter("Email"));
            userSpecificNode.setProperty("address",request.getParameter("Address"));
            session.save();
            return userSpecificNode.getName();
        }
    }

    public void createSimpleFormNode(Session session) throws RepositoryException {
        if(session.nodeExists("/content/practice")) {
            Node practiceNode = session.getNode("/content/practice");
            Node simpleFormNode = practiceNode.addNode("aem-simple-form-Info","nt:unstructured");
            session.save();
        }
    }

}
