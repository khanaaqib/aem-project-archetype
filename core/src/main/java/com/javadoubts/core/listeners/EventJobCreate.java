package com.javadoubts.core.listeners;

import com.adobe.xfa.Obj;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.util.HashMap;
import java.util.Map;


@Component(service = EventHandler.class,immediate = true,
        property = {
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",
                EventConstants.EVENT_FILTER+"=(path=/content/practice/us/en/demo/*)"
        }
)
public class EventJobCreate implements EventHandler {

    @Reference
    JobManager jobManager;

    @Override
    public void handleEvent(Event event) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("topic",event.getTopic());
        properties.put("pagePath", event.getProperty(SlingConstants.PROPERTY_PATH));
        properties.put("demoPage","demoPage");
        Job job = jobManager.addJob("event/job",properties);

    }
}
