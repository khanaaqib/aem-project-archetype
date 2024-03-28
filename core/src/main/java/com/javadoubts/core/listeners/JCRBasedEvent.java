package com.javadoubts.core.listeners;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

@Component(service = EventListener.class,immediate = true)
public class JCRBasedEvent implements EventListener {

    private Logger log = LoggerFactory.getLogger(JCRBasedEvent.class);

    private Session session;

    @Reference
    SlingRepository repository;

    @Activate
    protected void activate() throws RepositoryException {
        session = repository.loginService("site-map-service-user",null);
        session.getWorkspace().getObservationManager().addEventListener(
              this,
                Event.NODE_ADDED| Event.PROPERTY_ADDED,
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
                String pageUrl = events.nextEvent().getPath();
                log.info("page path value check:{}",pageUrl);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
