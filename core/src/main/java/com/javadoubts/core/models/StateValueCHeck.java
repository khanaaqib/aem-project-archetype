package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class StateValueCHeck {
    @Inject
    private String state;

    @Inject
    private List<CityValue> cityInformation;

    public List<CityValue> getCityInformation() {
        return cityInformation;
    }

    public String getState() {
        return state;
    }

}
