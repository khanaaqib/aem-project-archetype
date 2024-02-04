package com.javadoubts.core.schedulers;


import com.javadoubts.core.Config.FileSchedulerConfiguration;
import com.javadoubts.core.services.FirstService;
import com.javadoubts.core.services.TestService;
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

@Component(service = FileSchduler.class,immediate = true)
@Designate(ocd= FileSchedulerConfiguration.class)
public class FileSchduler implements Runnable{

    private Logger logger = LoggerFactory.getLogger(FileSchduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    FirstService firstService;

    @Reference
    TestService testService;

    @Activate
    protected  void activate(FileSchedulerConfiguration fileSchedulerConfiguration){
        addScheduler(fileSchedulerConfiguration);
    }

    public void addScheduler(FileSchedulerConfiguration fileSchedulerConfiguration){

        ScheduleOptions scheduleOptions = scheduler.EXPR(fileSchedulerConfiguration.cronJOb());
        scheduleOptions.name(fileSchedulerConfiguration.getSchedulerName());
        scheduler.schedule(this,scheduleOptions);
        logger.info("scheduler is running");
    }

    @Deactivate
    protected void deActivate(FileSchedulerConfiguration fileSchedulerConfiguration){
        removeSchduler(fileSchedulerConfiguration);
    }

    public void removeSchduler(FileSchedulerConfiguration fileSchedulerConfiguration){
        scheduler.unschedule(fileSchedulerConfiguration.getSchedulerName());
    }


    @Override
    public void run() {
         logger.info("Scheuler in run method");
        try {
            String message = firstService.getFileInput();
            String fname = testService.getFirstName();
            logger.info("message value check:{}",message);
            logger.info("first name value check:{}",fname);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
