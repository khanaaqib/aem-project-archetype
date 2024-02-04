package com.javadoubts.core.models;

import com.javadoubts.core.services.JanuaryComponentService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@Model(adaptables = Resource.class)
public class JanuaryAlternativeModel {

    @OSGiService
    JanuaryComponentService serviceMethod;

    private String message;

    @PostConstruct
    protected  void init() throws FileNotFoundException {
        message=serviceMethod.getFirstName();
    }

    public String getMessage() {
        return message;
    }
}
