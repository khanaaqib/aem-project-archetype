package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.FetchResourceService;
import com.javadoubts.core.services.RegistrationFormService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Component(service = RegistrationFormService.class)
public class RegistrationFormServiceImpl implements RegistrationFormService {

    @Reference
    FetchResourceService fetchResourceService;

    @Override
    public String addUserData(SlingHttpServletRequest request) throws RepositoryException {
        String createNode = StringUtils.EMPTY;
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        String enPagePath = "/content/practice/us/en" + "/" + "user-data";// /content/practice/us/en/user-data
        //Node randomNode = session.getNode(enPagePath);
        //Node ChildNode = randomNode.addNode("FirstName","un:unstructured");
        // randomNode.setProperty("abc","xyz");
        if(session.nodeExists(enPagePath)){
            createNode = addFormData(session,request,enPagePath);
        } else {
            addUserDataNode(session);
            createNode = addFormData(session,request,enPagePath);
        }

        return createNode;
    }

    public String addUserDataNode(Session session) throws RepositoryException {
        if(session.nodeExists("/content/practice/us/en")){
            Node enNode = session.getNode("/content/practice/us/en");
            Node userDataNode = enNode.addNode("user-data","nt:unstructured");
            session.save();
            return userDataNode.getName();
        }
        return null;
    }

    public String addFormData(Session session,SlingHttpServletRequest request, String enPagePath) throws RepositoryException {
        Node userDataNode = session.getNode(enPagePath);
        String childuserDataNode = getChildNode(request); //Aaqib-khan-abc@gmail.com
        if(!userDataNode.hasNode(childuserDataNode)){
               Node fordataNode = userDataNode.addNode(childuserDataNode,"nt:unstructured");//kirthi-abc-abc@gmail
               fordataNode.setProperty("firstName",request.getParameter("fname"));
               fordataNode.setProperty("lastName",request.getParameter("lname"));
               fordataNode.setProperty("Email",request.getParameter("email"));
               fordataNode.setProperty("Phone Number",request.getParameter("phoneNumber"));
               fordataNode.setProperty("Address",request.getParameter("address"));
               fordataNode.setProperty("ZipCode",request.getParameter("zipCode"));
               session.save();
               return fordataNode.getName() + "Added user";

        } else{
            return "user is already exist";
        }
    }

    public String getChildNode(SlingHttpServletRequest request){
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String childUserDataNode = fname + "-" + lname + "-" + email; ///content/practice/us/en/user-data/Aaqib-khan-abc@gmail.com
                                                                       ///content/practice/us/en/user-dat/Arshad-Khan-xyz@gmail.com
        return childUserDataNode;
    }

    @Override
    public List<Map<String,String>> getFormData() throws LoginException {
        List<Map<String,String>> list = new ArrayList<>();
        String userDataLocation = "/content/practice/us/en" + "/" + "user-data";
        ResourceResolver resourceResolver = fetchResourceService.getResourceResolver();
        Iterator<Resource> iterator = resourceResolver.getResource(userDataLocation).listChildren();
        while (iterator.hasNext()){
            Resource resource = iterator.next();
            Map<String,String> map  = new HashMap<>();
            ValueMap properties = resource.adaptTo(ValueMap.class);
            map.put("firstName",properties.get("firstName",String.class));
            map.put("lastName",properties.get("lastName",String.class));
            map.put("Email",properties.get("Email",String.class));
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String,String>> getUserRecord(String userData) throws LoginException {
        List<Map<String,String>> list = new ArrayList<>();
        String userDataLocation = "/content/practice/us/en/user-data" + "/" + userData;
        ResourceResolver resourceResolver = fetchResourceService.getResourceResolver();
        Resource resource = resourceResolver.getResource(userDataLocation);
        Map<String,String> map  = new HashMap<>();
        ValueMap properties = resource.adaptTo(ValueMap.class);
        map.put("firstName",properties.get("firstName",String.class));
        map.put("lastName",properties.get("lastName",String.class));
        map.put("Email",properties.get("Email",String.class));
        map.put("PhoneNumber",properties.get("Phone Number",String.class));
        map.put("Address",properties.get("Address",String.class));
        map.put("ZipCode",properties.get("ZipCode",String.class));
        list.add(map);
        return list;
    }

}
