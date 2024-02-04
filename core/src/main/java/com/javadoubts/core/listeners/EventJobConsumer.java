package com.javadoubts.core.listeners;

import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = JobConsumer.class,immediate = true,
        property = {
            JobConsumer.PROPERTY_TOPICS+"=event/job"
        }
)
public class EventJobConsumer implements JobConsumer {

    private Logger logger = LoggerFactory.getLogger(EventJobConsumer.class);

    private Session session;

    @Reference
    SlingRepository slingRepository;

    @Override
    public JobResult process(Job job) {

        try {
            session = slingRepository.loginService("practiceuserservice",null);
            String path = (String) job.getProperty("pagePath");
            String eventTopic = (String) job.getProperty("topic");
            String demoPage = (String) job.getProperty("demoPage");
            logger.info("event triggered successfully");
            Node node = session.getNode("/content/practice/us/en/demo");
            return JobResult.OK;
        } catch (RepositoryException e) {
            logger.info("repository error");
            return JobResult.FAILED;
        }

    }
}
