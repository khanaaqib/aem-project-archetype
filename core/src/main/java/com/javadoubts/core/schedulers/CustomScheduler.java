package com.javadoubts.core.schedulers;

import com.javadoubts.core.Config.CustomSchedulerServiceConfiguration;
import com.javadoubts.core.services.CustomOsgiService;
import com.javadoubts.core.services.FirstService;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

@Component(service = Runnable.class,immediate = true)
@Designate(ocd= CustomSchedulerServiceConfiguration.class)
public class CustomScheduler implements Runnable{

    private Logger logger = LoggerFactory.getLogger(CustomScheduler.class);

    @Reference
    CustomOsgiService service;

    @Reference
    FirstService firstService;

    @Reference
    Scheduler scheduler;

    @Activate
    protected void activate(CustomSchedulerServiceConfiguration configuration){
        addScheduler(configuration);
    }

    public void addScheduler(CustomSchedulerServiceConfiguration configuration){
        ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.getCronJob());
        scheduleOptions.name(configuration.getSchedulerName());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    protected void deactivate(CustomSchedulerServiceConfiguration configuration){
        removeScheduler(configuration);
    }

    public void removeScheduler(CustomSchedulerServiceConfiguration configuration){
        scheduler.unschedule(configuration.getSchedulerName());
    }

    @Override
    public void run() {
        logger.info("Custom Scheduler run method executed");
        String value = service.getValue();
        logger.info("CustomOSgiService Returned value check:{}", value);
        try {
            String fileVlaue = firstService.getFileInput();
            logger.info("file Returned value check:-->{}", fileVlaue);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
