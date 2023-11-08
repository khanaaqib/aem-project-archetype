package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class NestedTabInfo {

    @Inject
    private String nestedTitle;

    @Inject
    private String link;
    public String getNestedTitle() {
        return nestedTitle;
    }

    public String getLink() {
        return link;
    }


}
