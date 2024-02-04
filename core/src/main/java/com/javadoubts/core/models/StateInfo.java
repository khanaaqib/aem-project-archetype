package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class StateInfo {

    @Inject
    private String stateInfo;

    @Inject
    private String zipcode;

    @Inject
    private List<CityInfo> city;

    public String getStateInfo() {
        return stateInfo;
    }

    public String getZipcode() {
        return zipcode;
    }

    public List<CityInfo> getCity() {
        return city;
    }
}
