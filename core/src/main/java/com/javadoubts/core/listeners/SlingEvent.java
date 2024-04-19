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

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = EventHandler.class,immediate = true,
        property = {
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",//org.apache.sling.api.resource.Resource
                EventConstants.EVENT_FILTER+"=(path=/content/practice/us/java/*)"
        }
)
public class SlingEvent implements EventHandler {

    private Logger logger = LoggerFactory.getLogger(SlingEvent.class);

    @Reference
    SlingRepository slingRepository;

    private Session session;

    @Override
    public void handleEvent(Event event) {
        logger.info("page url check:{}",event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}
