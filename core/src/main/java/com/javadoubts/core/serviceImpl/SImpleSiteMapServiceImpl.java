package com.javadoubts.core.serviceImpl;

import com.adobe.xfa.Obj;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.javadoubts.core.services.SimpleSiteMapService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import java.util.*;

@Component(service = SimpleSiteMapService.class)
public class SImpleSiteMapServiceImpl implements SimpleSiteMapService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Reference
    QueryBuilder queryBuilder;


    @Override
    public ResourceResolver getResourceResolver() throws LoginException {
        ResourceResolver resourceResolver =null;
        Map<String, Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"site-map-service-user");
        resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
        return resourceResolver;
    }

    public Map<String,String> createQueryMap(){
        Map<String,String > map = new HashMap<>();
        map.put("type","cq:Page");
        map.put("path","/content/practice/us/en/demo");
        return map;
    }

    @Override
    public List<String> getResult() throws LoginException {
        List<String> list = new ArrayList<>();
        ResourceResolver resourceResolver = getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        Query query = queryBuilder.createQuery(PredicateGroup.create(createQueryMap()),session);
        SearchResult searchResult = query.getResult();
        Iterator<Resource> itr = searchResult.getResources();
        while (itr.hasNext()){
            Resource resource = itr.next();
            String pagePathUrl = "http://localhost:4502"+resource.getPath();
            list.add(pagePathUrl);
        }
        return list;
    }
}
