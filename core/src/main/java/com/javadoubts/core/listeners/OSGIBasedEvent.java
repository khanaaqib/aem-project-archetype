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
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",
                EventConstants.EVENT_FILTER+"=(path=/content/practice/us/en/*)"
        }
)
public class OSGIBasedEvent implements EventHandler {

    private Logger logger = LoggerFactory.getLogger(OSGIBasedEvent.class);

    @Reference
    SlingRepository slingRepository;

    private Session session;



    @Override
    public void handleEvent(Event event) {
        try {
            session = slingRepository.loginService("practiceuserservice",null);
            Node node = session.getNode("/content/practice/us/en/aempage");
            if(node.hasNode("last-name")){
                Node ChildNode = session.getNode("/content/practice/us/en/aempage/last-name");
                ChildNode.getProperty("firstname");
                ChildNode.setProperty("email","abc@gmail.com");
            } else{
                Node childNode = node.addNode("last-name","nt:unstructured");
                childNode.setProperty("firstName","Aaqib");
                childNode.setProperty("lasttName","Khan");
            }

            session.save();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        logger.info("topic value check :{} path value check:{}",event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}
