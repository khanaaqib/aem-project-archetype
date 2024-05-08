package com.javadoubts.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class,immediate = true,
        property = {
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/CHANGED",
                EventConstants.EVENT_FILTER+"=(path=/content/practice/us/en/*)"//org.apache.sling.api.resource.Resource
        }
)
public class ResourceTypeEvent implements EventHandler {

    private Logger logger= LoggerFactory.getLogger(ResourceTypeEvent.class);

    @Override
    public void handleEvent(Event event) {
         logger.info("topic Value check:{}, pagePath value check::{}",event.getTopic(),event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}
