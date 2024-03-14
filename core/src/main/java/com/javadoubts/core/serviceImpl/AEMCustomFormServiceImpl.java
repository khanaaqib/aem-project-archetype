package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.AEMCustomFormService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;


@Component(service = AEMCustomFormService.class)
public class AEMCustomFormServiceImpl implements AEMCustomFormService {

    @Override
    public String addUsertoNode(SlingHttpServletRequest request) {
        String nodeCreate = StringUtils.EMPTY;
        String nodeLocation=null;
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        if(request.getParameter("user").equals("aem-user-form")) {
           nodeLocation = "/content/practice/aem-user-form";
        }else{
            nodeLocation = "/content/practice/us/user-data";
        }
        try {
            if (session.nodeExists(nodeLocation)) {
                nodeCreate = CreateUserSpecificNode(session, nodeLocation, request);
            } else {
                CreateUserFormNode(session);
                nodeCreate = CreateUserSpecificNode(session, nodeLocation, request);
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return nodeCreate;
    }

    public String CreateUserSpecificNode(Session session, String nodeLocation, SlingHttpServletRequest request) throws RepositoryException {
        String email = request.getParameter("Email");
        Node userNode = session.getNode(nodeLocation);
        if (userNode.hasNode(email)) {
            return "user is present";
        } else {
            Node userSpecificNode = userNode.addNode(email, "nt:unstructured");
            if(request.getParameter("firstName")!=null) {
                userSpecificNode.setProperty("firstName", request.getParameter("firstName"));
            } if(request.getParameter("lastName")!=null){
                userSpecificNode.setProperty("lastName", request.getParameter("lastName"));
            } if(request.getParameter("Email")!=null){
                userSpecificNode.setProperty("email", request.getParameter("Email"));
            } if(request.getParameter("Address")!=null){
                userSpecificNode.setProperty("address", request.getParameter("Address"));
            }
            session.save();
            return userSpecificNode.getName();
        }


    }
    public void CreateUserFormNode(Session session) throws RepositoryException {
        if(session.nodeExists("/content/practice")){
            Node praticeNode = session.getNode("/content/practice");
            Node aemUserFormNode = praticeNode.addNode("aem-user-form","nt:unstructured");
            session.save();
        }
    }
}
