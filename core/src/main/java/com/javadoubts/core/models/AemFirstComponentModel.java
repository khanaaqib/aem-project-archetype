package com.javadoubts.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class AemFirstComponentModel {

    @Inject
    private List<StateInformationModel> StateInformation;

    public List<StateInformationModel> getStateInformation() {
        return StateInformation;
    }
}
