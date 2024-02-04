package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class CityInformationFields {
    @Inject
    private String city;

    public String getCity() {
        return city;
    }
}
