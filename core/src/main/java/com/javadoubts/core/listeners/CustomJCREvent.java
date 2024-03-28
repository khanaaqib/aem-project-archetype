package com.javadoubts.core.listeners;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

@Component(service = EventListener.class,immediate = true)
public class CustomJCREvent implements EventListener {

    private Logger logger = LoggerFactory.getLogger(CustomJCREvent.class);

    private Session session;

    @Reference
    SlingRepository slingRepository;


    @Reference
    SlingHttpServletRequest request;

    @Activate
    protected  void activate() throws RepositoryException {
        session= slingRepository.loginService("eventsystemuser",null);
        session.getWorkspace().getObservationManager().addEventListener(
                this,
                Event.NODE_ADDED,
                "/content/practice/us/en/eventpage",
                true,
                null,
                null,
                false
        );
    }

    @Override
    public void onEvent(EventIterator events) {

       while(events.hasNext()){
           try {
               String pagePath = events.nextEvent().getPath();
               if(pagePath.contains("jcr:content")) {
                   logger.info("inside jcr content");
                   Node node = session.getNode(pagePath);
                   node.setProperty("firstName", "aaqib");
                   session.save();
               }
               logger.info("page path check for jcr event::{}",pagePath);
           } catch (RepositoryException e) {
               throw new RuntimeException(e);
           }
       }

    }
}
