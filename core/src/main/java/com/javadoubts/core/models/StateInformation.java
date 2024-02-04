package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class StateInformation {
    @Inject
    private String state;

    @Inject
    private String cm;

    @Inject
    private List<CityInformationFields> cityInfo;

    public String getState() {
        return state;
    }

    public String getCm() {
        return cm;
    }

    public List<CityInformationFields> getCityInfo() {
        return cityInfo;
    }
}
