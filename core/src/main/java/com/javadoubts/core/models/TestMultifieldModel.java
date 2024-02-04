package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class TestMultifieldModel {


    @Inject
    private List<StateInfo> state;

    public List<StateInfo> getState() {
        return state;
    }
}
