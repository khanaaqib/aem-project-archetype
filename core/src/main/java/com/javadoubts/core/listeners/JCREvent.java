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
public class JCREvent implements EventListener {

    private Logger log = LoggerFactory.getLogger(JCREvent.class);

    @Reference
    SlingRepository slingRepository;

    private Session session;

    @Activate
    protected void activate() throws RepositoryException {
        session = slingRepository.loginService("jcreventservice",null);
        session.getWorkspace().getObservationManager().addEventListener(
                this,
                Event.PROPERTY_ADDED|Event.NODE_ADDED,
                "/content/practice/us",
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
                log.info("pageUrl value check for onEvent method:{}",pageUrl);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
