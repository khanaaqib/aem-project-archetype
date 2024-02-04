package com.javadoubts.core.services;

import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONArray;
import org.json.JSONException;

import javax.jcr.RepositoryException;

public interface QueryBuilderSearchService {
    public JSONArray getSearchResult(ResourceResolver resourceResolver) throws RepositoryException, JSONException;
}
