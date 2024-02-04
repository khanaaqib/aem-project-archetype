package com.javadoubts.core.models;

import com.javadoubts.core.services.BasicOsgiService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class BasicOSgiModel {

    @OSGiService
    BasicOsgiService basicOsgiService;

    private String message;

    @PostConstruct
    protected void init(){
        message = basicOsgiService.getUserData();

    }

    public String getMessage() {
        return message;
    }
}
