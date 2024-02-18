package com.javadoubts.core.servlets;

import com.javadoubts.core.services.FirstAEMService;
import com.javadoubts.core.services.TestAEMService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "practice/components/page"
)
public class FirstAEMServlet extends SlingAllMethodsServlet {

    private Logger log = LoggerFactory.getLogger(FirstAEMServlet.class);

    @Reference
    FirstAEMService serviceValue;

    @Reference
    TestAEMService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String pagePath = request.getParameter("page");
        String address= serviceValue.getAddress();
        String fullName = service.getUserInfo();
        log.info("pagePath value:{} address value :{} full name value:{}",pagePath,address,fullName);
        response.getWriter().write("pagePath" +" "+ pagePath + " " + "AddressValueCheck" + " " + address + " " + "fullName Value check" + " " + fullName);
    }
}
