package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class ParentTitleInfo {
     @Inject
     private String tab1title;

    @Inject
    private List<NestedTabInfo> nestedTabInfo;
    public String getTab1title() {
        return tab1title;
    }

    public List<NestedTabInfo> getNestedTabInfo() {
        return nestedTabInfo;
    }



}
