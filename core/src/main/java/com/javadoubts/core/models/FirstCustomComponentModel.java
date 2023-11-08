package com.javadoubts.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class FirstCustomComponentModel {

    @Inject
    private List<ContactInfo> contact;

    public List<ContactInfo> getContact() {
        return contact;
    }
}
