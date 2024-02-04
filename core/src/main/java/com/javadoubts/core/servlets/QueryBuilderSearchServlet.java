package com.javadoubts.core.servlets;

import com.javadoubts.core.services.QueryBuilderSearchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/query/QueryBuilderServlet"
)
public class QueryBuilderSearchServlet extends SlingAllMethodsServlet {

    @Reference
    QueryBuilderSearchService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        JSONArray pagePathvalue=null;
        try {
            pagePathvalue = service.getSearchResult(resourceResolver);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(pagePathvalue.toString());
    }
}
