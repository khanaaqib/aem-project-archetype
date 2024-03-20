package com.javadoubts.core.servlets;

import com.javadoubts.core.services.CustomSiteMapService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@Component(service = Servlet.class)
@SlingServletPaths(
        value ="/bin/site/sitemapServlet"
)
public class SiteMapServlet extends SlingAllMethodsServlet {
    @Reference
    CustomSiteMapService customSiteMapService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        List<String> message=null;
        try {
            message= customSiteMapService.getPageResult();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write(message.toString());
    }
}
