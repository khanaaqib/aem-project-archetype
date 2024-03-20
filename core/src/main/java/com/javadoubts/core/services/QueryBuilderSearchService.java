package com.javadoubts.core.services;

import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONArray;
import org.json.JSONException;

import javax.jcr.RepositoryException;
import java.io.FileNotFoundException;

public interface QueryBuilderSearchService {
    public void getSearchResult(ResourceResolver resourceResolver) throws RepositoryException, JSONException, FileNotFoundException;
}
