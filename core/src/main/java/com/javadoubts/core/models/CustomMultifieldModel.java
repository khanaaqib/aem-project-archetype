package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomMultifieldModel {

    @Inject
    private List<State> stateInformation;

    public List<State> getStateInformation() {
        return stateInformation;
    }
}
