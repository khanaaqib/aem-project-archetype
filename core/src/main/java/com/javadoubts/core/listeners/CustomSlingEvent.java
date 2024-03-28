package com.javadoubts.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
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
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",
                EventConstants.EVENT_FILTER+"=(path=/content/practice/us/en/*)"
        }
)
public class CustomSlingEvent implements EventHandler {

    @Reference
    SlingHttpServletRequest request;

    private Logger logger= LoggerFactory.getLogger(CustomSlingEvent.class);

    @Override
    public void handleEvent(Event event) {
         logger.info("event triggered");
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        try {
            Node node = session.getNode(String.valueOf(event.getProperty(SlingConstants.PROPERTY_PATH)));
            node.setProperty("email","aaqib123@gmail.com");
            session.save();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        logger.info("topic value check:{}, property value check:{}",event.getTopic(),event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}
