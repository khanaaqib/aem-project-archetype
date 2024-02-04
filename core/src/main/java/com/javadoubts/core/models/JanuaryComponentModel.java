package com.javadoubts.core.models;

import com.javadoubts.core.services.JanuaryComponentService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.util.List;

@Model(adaptables = Resource.class)
public class JanuaryComponentModel {

    @OSGiService
    JanuaryComponentService januaryComponentService;

    private String firstNameFetch;

    @Inject
    private List<StateValueCHeck> stateInformation;

    public List<StateValueCHeck> getStateInformation() {
        return stateInformation;
    }

    @PostConstruct
    protected void init() throws FileNotFoundException {
        firstNameFetch= januaryComponentService.getFirstName();
    }

    public String getFirstNameFetch() {
        return firstNameFetch;
    }
//10 record we are fetching
}
