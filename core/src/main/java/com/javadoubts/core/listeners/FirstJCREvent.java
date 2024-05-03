package com.javadoubts.core.listeners;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

@Component(service = EventListener.class,immediate = true)
public class FirstJCREvent implements EventListener {

    private Logger logger = LoggerFactory.getLogger(FirstJCREvent.class);
    private Session session;

    @Reference
    SlingRepository slingRepository;


    @Activate

    protected void activate() throws RepositoryException {
        session = slingRepository.loginService("customeventservice",null);
        session.getWorkspace().getObservationManager().addEventListener(
                this,
                Event.PROPERTY_ADDED|Event.PROPERTY_CHANGED|Event.NODE_ADDED,
                "/content/practice/us/en/testdemo",
                true,
                null,
                null,
                false
        );
    }
    @Override
    public void onEvent(EventIterator events) {
       while (events.hasNext()){
           try {
               String pagePathValue = events.nextEvent().getPath();
               int eventType= events.nextEvent().getType();
               logger.info("checking Node url:{}, type of event:{}",pagePathValue,eventType);
           } catch (RepositoryException e) {
               throw new RuntimeException(e);
           }
       }

    }
}
