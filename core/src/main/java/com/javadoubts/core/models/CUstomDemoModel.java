package com.javadoubts.core.models;

import com.javadoubts.core.services.CustoemDemoService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@Model(adaptables = Resource.class)
public class CUstomDemoModel {

    @OSGiService
    CustoemDemoService service;

    private String name;

    @PostConstruct
    protected void init()  {

        name= service.getFileInfo();

    }

    public String getName() {
        return name;
    }
}
