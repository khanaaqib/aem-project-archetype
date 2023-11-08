package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class CityInformationCheck {
    @Inject
    private String city;

    @Inject
    private String zipcode;

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}
