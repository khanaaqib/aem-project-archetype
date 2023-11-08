package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class DemoModel {

    @Inject
    private List<Contact> contact;

    public List<Contact> getContact() {
        return contact;
    }
}
