package com.javadoubts.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class,immediate = true,
        property = {
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",
                EventConstants.EVENT_FILTER+"=(path=/content/practice/us/en/process2/*)"
        }
)
public class SimpleSlingBasedEvent implements EventHandler {

    private Logger logger = LoggerFactory.getLogger(SimpleSlingBasedEvent.class);

    @Override
    public void handleEvent(Event event) {
       logger.info("topic:{}   path:{}",event.getTopic(),event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}
