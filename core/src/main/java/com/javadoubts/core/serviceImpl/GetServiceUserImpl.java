package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.GetServiceUser;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

@Component(service = GetServiceUser.class)
public class GetServiceUserImpl implements GetServiceUser {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public ResourceResolver fetchResourceResolver() {
        ResourceResolver resourceResolver=null;
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"my-custom-system-user");
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
        }
        catch (LoginException e){
            e.getMessage();
        }
        return  resourceResolver;
    }
}
