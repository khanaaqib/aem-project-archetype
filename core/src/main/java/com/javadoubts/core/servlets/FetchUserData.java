package com.javadoubts.core.servlets;

import com.javadoubts.core.services.CustomFormService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/table/fetchuserdata"
)
public class FetchUserData extends SlingAllMethodsServlet {

    @Reference
    CustomFormService customFormService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        List<Map<String,String>> userData = null;

    }
}
