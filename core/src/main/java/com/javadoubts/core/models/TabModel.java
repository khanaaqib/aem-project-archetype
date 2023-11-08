package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class TabModel {

    @Inject
    private List<ParentTitleInfo> tab1;

    public List<ParentTitleInfo> getTab1() {
        return tab1;
    }
}
