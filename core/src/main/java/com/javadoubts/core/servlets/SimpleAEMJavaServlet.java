package com.javadoubts.core.servlets;

import com.javadoubts.core.configuration.SimpleAEMJavaOsgiConfiguration;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = "/bin/Simple/JavaServlet"
)
@Designate(ocd = SimpleAEMJavaOsgiConfiguration.class)
public class SimpleAEMJavaServlet extends SlingAllMethodsServlet {


    String url="";
    String key="";
    @Activate
    protected void activate(SimpleAEMJavaOsgiConfiguration configuration){
        url = configuration.getURLEndpoint();
        key= Arrays.toString(configuration.getClientId());
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("url endpoint check:::::" + " " + url + " " + "client id value check:::::" +" "+ key);
    }
}
