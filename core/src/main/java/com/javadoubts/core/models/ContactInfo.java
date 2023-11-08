package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class ContactInfo {
    @Inject
    private String state;

    @Inject
    private List<CityInformation> cityInfo;

    public List<CityInformation> getCityInfo() {
        return cityInfo;
    }

    public String getState() {
        return state;
    }
}
