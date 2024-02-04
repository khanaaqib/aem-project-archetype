package com.javadoubts.core.listeners;

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
public class JCRBasedApiEvent implements EventListener {

    private Logger logger = LoggerFactory.getLogger(JCRBasedApiEvent.class);

    private Session session;

    @Reference
    SlingRepository slingRepository;

    @Activate
    protected void activate() throws RepositoryException {
       session = slingRepository.loginService("aemhshsuhi",null);
       session.getWorkspace().getObservationManager().addEventListener(
               this,
               Event.NODE_ADDED | Event.NODE_REMOVED,
               "/content/practice/us/en/process2",
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

                 String pagePath = events.nextEvent().getPath();
                 String userId = events.nextEvent().getUserID();
                 logger.info("page path name:{} user id value check:{}",pagePath,userId);
             } catch (RepositoryException e) {
                 throw new RuntimeException(e);
             }
         }
    }
}
