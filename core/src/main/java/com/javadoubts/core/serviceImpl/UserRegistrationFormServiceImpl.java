package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.UserRegistrationFormService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Component(service = UserRegistrationFormService.class)
public class UserRegistrationFormServiceImpl implements UserRegistrationFormService {
    @Override
    public String addUserDataToNode(SlingHttpServletRequest request) throws RepositoryException {
        String nodeCreated = StringUtils.EMPTY;
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        String nodeLocation = "/content/practice" + "/" + "user-registration-data"; //content/practice/user-registration-data
        if (session.nodeExists(nodeLocation)) {
            nodeCreated= addUserSpecificData(session,request,nodeLocation);
        } else {
            addUserRegistrationNode(session);
            nodeCreated= addUserSpecificData(session,request,nodeLocation);
        }
        return nodeCreated;
    }

    public String addUserRegistrationNode(Session session) throws RepositoryException {
        if (session.nodeExists("/content/practice")) {
            Node practiceNode = session.getNode("/content/practice");
            Node userDataNode = practiceNode.addNode("user-registration-data", "nt:unstructured");
            session.save();
            return userDataNode.getName();
        }
       return null;
    }

    public String addUserSpecificData(Session session, SlingHttpServletRequest request,String nodeLocation) throws RepositoryException {
           Node userDataNode = session.getNode(nodeLocation);
           String fulldetail = getUserNodeName(request);
           if(!userDataNode.hasNode(fulldetail)) {
               Node userspecificNode = userDataNode.addNode(fulldetail, "nt:unstructured");
               userspecificNode.setProperty("firstName", request.getParameter("firstName"));
               userspecificNode.setProperty("lastName", request.getParameter("lastName"));
               userspecificNode.setProperty("email", request.getParameter("email"));
               userspecificNode.setProperty("contactinfo", request.getParameter("contactNo"));
               userspecificNode.setProperty("address", request.getParameter("address"));
               userspecificNode.setProperty("zipcode", request.getParameter("zipcode"));
               userspecificNode.setProperty("comment", request.getParameter("Comment"));
               session.save();
               return userspecificNode.getName() + "added";
           } else{
               return "user is already registered";
           }
    }

    public String getUserNodeName(SlingHttpServletRequest request){
        String  firstName = request.getParameter("firstName");
        String  lastName = request.getParameter("lastName");
        String  email = request.getParameter("email");
        return firstName + "-" + lastName + "-" + email;
    }

    @Override
    public List fetchAllrecords(SlingHttpServletRequest request) throws RepositoryException {
        String nodeLocation ="/content/practice/user-registration-data";
        List<Map<String,String>> listValue = new ArrayList<>();
        ResourceResolver resourceResolver = request.getResourceResolver();
        Iterator<Resource> itr = resourceResolver.getResource(nodeLocation).listChildren();

        while (itr.hasNext()){
            Resource resource = itr.next();
            ValueMap prop = resource.adaptTo(ValueMap.class);
            Map<String, String> mapValue = new HashMap<>();
            mapValue.put("firstName",prop.get("firstName",String.class));
            mapValue.put("lastName",prop.get("lastName",String.class));
            mapValue.put("email",prop.get("email",String.class));
            mapValue.put("conactNo",prop.get("contactinfo",String.class));
            listValue.add(mapValue);
        }
        return listValue;
    }
}
