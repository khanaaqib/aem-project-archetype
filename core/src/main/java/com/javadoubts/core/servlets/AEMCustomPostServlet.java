package com.javadoubts.core.servlets;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.tika.parser.epub.EpubContentParser;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
@Component(service = Servlet.class)
@SlingServletPaths(
        value="/bin/post/customServlet"
)
public class AEMCustomPostServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String nodeCreate= StringUtils.EMPTY;
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        String nodeLocation = "/content/practice/aem-user-form";
        try{
            if(session.nodeExists(nodeLocation)){
                nodeCreate = CreateUserSpecificNode(session,nodeLocation,request);
            } else {
               CreateUserFormNode(session);
               nodeCreate = CreateUserSpecificNode(session,nodeLocation,request);
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(nodeCreate);



    }
    public void CreateUserFormNode(Session session) throws RepositoryException {
         if(session.nodeExists("/content/practice")){
             Node praticeNode = session.getNode("/content/practice");
             Node aemUserFormNode = praticeNode.addNode("aem-user-form","nt:unstructured");
             session.save();
         }
    }

    public String CreateUserSpecificNode(Session session , String nodeLocation, SlingHttpServletRequest request) throws RepositoryException {
        String email = request.getParameter("Email");
        Node userNode = session.getNode(nodeLocation);
        if (userNode.hasNode(email)) {
            return "user is present";
        } else {
            Node userSpecificNode = userNode.addNode(email, "nt:unstructured");
            userSpecificNode.setProperty("firstName",request.getParameter("firstName"));
            userSpecificNode.setProperty("lastName",request.getParameter("lastName"));
            userSpecificNode.setProperty("email",request.getParameter("Email"));
            userSpecificNode.setProperty("address",request.getParameter("Address"));
            session.save();
            return userSpecificNode.getName();
        }


    }
}
