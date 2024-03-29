package com.javadoubts.core.schedulers;

import com.javadoubts.core.configuration.SimpleSchedulerConfiguration;
import com.javadoubts.core.services.SimpleJavaOsgiService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.List;

@Component(service =Runnable.class,immediate = true)
@Designate(ocd= SimpleSchedulerConfiguration.class)
public class SimpleJavaScheduler implements Runnable{

    private Logger logger = LoggerFactory.getLogger(SimpleJavaScheduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    SimpleJavaOsgiService service;

    @Activate
    protected void activate(SimpleSchedulerConfiguration configuration){
        addScheduler(configuration);
    }

    public void addScheduler(SimpleSchedulerConfiguration configuration){
        ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.getCronJob());
        scheduleOptions.name(configuration.getSchedulerName());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    public void deactivate(SimpleSchedulerConfiguration configuration){
        removeScheduler(configuration);
    }

    public void removeScheduler(SimpleSchedulerConfiguration configuration){
        scheduler.unschedule(configuration.getSchedulerName());
    }

    @Override
    public void run() {
       logger.info("inside run method of Simple Java Scheduler aaqib test");
        try {
            List<String> list= service.getPageResult();
            logger.info("page path value check in SimpleJavaScheduler:::{}",list);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}
