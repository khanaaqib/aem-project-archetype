package com.javadoubts.core.servlets;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
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
        value = "/bin/user/userForm"
)
public class RegirstrationFormServlet extends SlingAllMethodsServlet {
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
         String nodeCreate = StringUtils.EMPTY;
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        String nodelocation= "/content/practice/us/user-data";
        // /content/practice/us/user-data
        try {
            if(session.nodeExists(nodelocation)){
                nodeCreate = createUserSpecificNode(session,request,nodelocation);
            } else{
                CreateNode(session);// /content/practice/us/user-data/ aaqib, vijay

                nodeCreate = createUserSpecificNode(session,request,nodelocation);
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(nodeCreate);
    }

    public void CreateNode(Session session) throws RepositoryException {
        if(session.nodeExists("/content/practice/us")){
              Node node  = session.getNode("/content/practice/us");
              Node userDataNode = node.addNode("user-data","nt:unstructured");
              session.save();
        }
    }

    public String createUserSpecificNode(Session session, SlingHttpServletRequest request, String nodeLocation) throws RepositoryException {
        Node userDataNode = session.getNode(nodeLocation);
        String firstName = request.getParameter("firstName");
        if(!userDataNode.hasNode(firstName)){
            Node userSpecificeNode = userDataNode.addNode(firstName,"nt:unstructured");
            userSpecificeNode.setProperty("firstName",firstName);
            userSpecificeNode.setProperty("lastName",request.getParameter("lastName"));
            userSpecificeNode.setProperty("email",request.getParameter("email"));
            session.save();
            return  userSpecificeNode.getName();
        } else {
            return "user is already present";
        }
    }
}
