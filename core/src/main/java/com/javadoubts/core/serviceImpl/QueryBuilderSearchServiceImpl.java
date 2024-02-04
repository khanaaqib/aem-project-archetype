package com.javadoubts.core.serviceImpl;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.javadoubts.core.services.QueryBuilderSearchService;
import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = QueryBuilderSearchService.class)
public class QueryBuilderSearchServiceImpl implements QueryBuilderSearchService {

    @Reference
    QueryBuilder queryBuilder;

    @Override
    public JSONArray getSearchResult( ResourceResolver resourceResolver) throws RepositoryException, JSONException {
        Map<String, String> queryMapVlaue = new HashMap<>();
        queryMapVlaue.put("type","cq:Page");
        queryMapVlaue.put("path","/content/practice/us/en/aempage");
        Session session = resourceResolver.adaptTo(Session.class);
        Query query = queryBuilder.createQuery(PredicateGroup.create(queryMapVlaue),session);
        SearchResult result = query.getResult();
        List<Hit> hits = result.getHits();
        JSONArray jsonArray = new JSONArray();
        for(Hit hit: hits){
            Page page = hit.getResource().adaptTo(Page.class);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("page",page.getPath());
            jsonObject.put("date",page.getLastModified().getTime());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
