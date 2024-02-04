package com.javadoubts.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.jcr.Session;
import java.util.List;

@Model(adaptables = Resource.class)
public class BasicComponentModel {
   @Inject
   private List<StateInformation> stateInfo;

   public List<StateInformation> getStateInfo() {
      return stateInfo;
   }
}
