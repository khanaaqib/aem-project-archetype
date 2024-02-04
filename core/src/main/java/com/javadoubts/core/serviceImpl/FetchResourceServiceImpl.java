package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.FetchResourceService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

@Component(service = FetchResourceService.class)
public class FetchResourceServiceImpl implements FetchResourceService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public ResourceResolver getResourceResolver()  {
        ResourceResolver resourceResolver=null;
        Map<String,Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE, "practiceuserservice");
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
        }catch (LoginException e){
            e.printStackTrace();
        }
        return resourceResolver;
    }
}
