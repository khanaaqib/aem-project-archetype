package com.javadoubts.core.listeners;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component(service = ResourceChangeListener.class,immediate = true,
          property = {
             ResourceChangeListener.PATHS+"=/content/practice/us/en/listner",
             ResourceChangeListener.CHANGES+"="+"ADDED",
             ResourceChangeListener.CHANGES+"="+"CHANGED",
             ResourceChangeListener.CHANGES+"="+"REMOVED"

          }


)
public class SlingBasedApi implements ResourceChangeListener {

    private Logger logger = LoggerFactory.getLogger(SlingBasedApi.class);

    @Override
    public void onChange(List<ResourceChange> list) {
        for(ResourceChange resourceChange: list){
            logger.info("page path value check:{}",resourceChange.getPath());
        }
    }
}
