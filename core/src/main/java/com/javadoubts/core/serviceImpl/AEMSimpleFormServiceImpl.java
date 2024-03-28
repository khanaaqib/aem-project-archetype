package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.AEMSimpleFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = AEMSimpleFormService.class)
public class AEMSimpleFormServiceImpl implements AEMSimpleFormService {
    @Override
    public String addFormDataToNode(SlingHttpServletRequest request) {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        String createdNode ="";
        String nodelocation="";
        if(request.getParameter("user").equals("aem-simple-form-Info")) {
            nodelocation = "/content/practice/aem-simple-form-Info";
        } else{
            nodelocation = "/content/practice/us/user-data";
        }
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
        return createdNode;
    }

    public void createSimpleFormNode(Session session) throws RepositoryException {
        if(session.nodeExists("/content/practice")) {
            Node practiceNode = session.getNode("/content/practice");
            Node simpleFormNode = practiceNode.addNode("aem-simple-form-Info","nt:unstructured");
            session.save();
        }
    }

    public String CreateUserSpecificNode(Session session, SlingHttpServletRequest request, String nodelocation) throws RepositoryException {
        String email = request.getParameter("Email");
        Node simpleFormNode = session.getNode(nodelocation);
        if(simpleFormNode.hasNode(email)){
            return "user is already exist";
        } else{
            Node userSpecificNode = simpleFormNode.addNode(email,"nt:unstructured");
            if(request.getParameter("firstName")!=null){
                userSpecificNode.setProperty("firstName", request.getParameter("firstName"));
            }if(request.getParameter("lastName")!=null) {
                userSpecificNode.setProperty("lastName", request.getParameter("lastName"));
            }if(request.getParameter("Email")!=null){
                userSpecificNode.setProperty("email",request.getParameter("Email"));
            }if(request.getParameter("Address")!=null){
                userSpecificNode.setProperty("address",request.getParameter("Address"));
            }
            session.save();
            return userSpecificNode.getName();
        }
    }
}
