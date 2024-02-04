package com.javadoubts.core.models;

import com.javadoubts.core.services.DemoService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class TabComponentModel {

    @OSGiService
    //DemoService demo;
    private String message;


    @PostConstruct
    protected void init(){
       // message = demo.getName();
    }

    public String getMessage() {
        return message;
    }





}
