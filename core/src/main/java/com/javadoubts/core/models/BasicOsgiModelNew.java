package com.javadoubts.core.models;

import com.javadoubts.core.services.BasicOsgiService;
import com.javadoubts.core.services.FirstNameOsgiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class BasicOsgiModelNew {
    @OSGiService
    BasicOsgiService basicOsgiService;

    @OSGiService
    FirstNameOsgiService firstNameOsgiService;

    SlingHttpServletRequest slingHttpServletRequest;

    SlingHttpServletResponse slingHttpServletResponse;

    private String detail;

    private String fileInfo;

    private String firstName;

    private String pagePath;

    @PostConstruct
    protected void init(){
        detail = basicOsgiService.getUserData();
        fileInfo = basicOsgiService.getMessage();
        firstName = firstNameOsgiService.getFirstName();
        pagePath = slingHttpServletRequest.getParameter("Page");
    }

    public String getDetail() {
        return detail;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPagePath() {
        return pagePath;
    }
}
