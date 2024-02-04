package com.javadoubts.core.models;

import com.javadoubts.core.services.FirstService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@Model(adaptables = Resource.class)
public class FIrstModel {

    private String message;

    @OSGiService
    FirstService firstService;


    @PostConstruct
    protected void init() throws FileNotFoundException {
        message = firstService.getFileInput();
    }

    public String getMessage() {
        return message;
    }
}
