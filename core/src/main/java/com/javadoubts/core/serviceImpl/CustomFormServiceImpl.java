package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.CustomFormService;
import com.javadoubts.core.services.FetchResourceService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Item;
import javax.jcr.RepositoryException;
import java.util.*;

@Component(service = CustomFormService.class)
public class CustomFormServiceImpl implements CustomFormService {

    @Reference
    FetchResourceService fetchResourceService;
    @Override
    public String getUserData(SlingHttpServletRequest request) throws LoginException, RepositoryException {
        return null;
    }

    @Override
    public List<Map<String,String>> fetchuserData() throws RepositoryException, LoginException {
        List<Map<String,String>> list = new ArrayList<>();
        String nodeLocation =  "/content/practice/us/en" + "/" + "user-data";
        Iterator<Resource> iterator =  fetchResourceService.getResourceResolver().getResource(nodeLocation).listChildren();
        while (iterator.hasNext()){
            Resource resource = iterator.next();
            Map<String,String> map = new HashMap<>();
            ValueMap properties = resource.adaptTo(ValueMap.class);
            map.put("firstName",properties.get("firstName",String.class));
            map.put("lastName",properties.get("lastName",String.class));;
            map.put("Email",properties.get("Email",String.class));
            map.put("phoneNumber",properties.get("Phone Number",String.class));
            list.add(map);

        }
        return list;
    }
}
