package com.javadoubts.core.schedulers;

import com.javadoubts.core.serviceImpl.TrainingSchedulerConfig;
import com.javadoubts.core.services.DemoService;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = TrainingScheduler.class, immediate = true)
@Designate(ocd = TrainingSchedulerConfig.class)
public class TrainingScheduler implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(TrainingScheduler.class);

    @Reference
    DemoService demoService;

    @Reference
    private Scheduler scheduler;
    @Activate
    protected void activate(TrainingSchedulerConfig config){
        logger.info("inside activate method");
        addSchduler(config);
    }

    public void addSchduler(TrainingSchedulerConfig config){
        logger.info("inside addSchduler method");
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.getCronJob());
        scheduleOptions.name(config.getShchedulerName());
        scheduler.schedule(this,scheduleOptions);
    }


    @Deactivate
    protected void deactivate(final TrainingSchedulerConfig config){
        removeScheduler(config);
    }

    public void removeScheduler(TrainingSchedulerConfig config){
        scheduler.unschedule(config.getShchedulerName());
    }



    @Override
    public void run() {
        String message = demoService.getFileInfo();
        logger.info("file message:{}",message);
    }
}
