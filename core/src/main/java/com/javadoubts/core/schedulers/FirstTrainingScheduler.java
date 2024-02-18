package com.javadoubts.core.schedulers;

import com.javadoubts.core.Config.FirstTrainingSchedulerConfiguration;
import com.javadoubts.core.services.FirstNameOsgiService;
import com.javadoubts.core.services.FirstTrainingService;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class,immediate = true)
@Designate(ocd = FirstTrainingSchedulerConfiguration.class)
public class FirstTrainingScheduler implements Runnable{

    private Logger logger = LoggerFactory.getLogger(FirstTrainingScheduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    FirstTrainingService training;

    @Reference
    FirstNameOsgiService firstNameOsgiService;

    @Activate
    protected void activate(FirstTrainingSchedulerConfiguration configuration){
        addScheduler(configuration);
    }

    public void addScheduler(FirstTrainingSchedulerConfiguration configuration){
        ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.getSchedulerTime());
        scheduleOptions.name(configuration.getSchedulerName());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    protected void deactivate(FirstTrainingSchedulerConfiguration configuration){
        removeScheduler(configuration);
    }

    public void removeScheduler(FirstTrainingSchedulerConfiguration schedulerConfiguration){
        scheduler.unschedule(schedulerConfiguration.getSchedulerName());
    }




    @Override
    public void run() {
       logger.info("FirstTrainingScheduler run method will trigger");
       String trainingresponse = training.getFullName();
        logger.info("Full name CHeck:{}",trainingresponse);
        String fileMessage= training.getFileValue();
        logger.info("local data fetch:{}",fileMessage);
        String firstName = firstNameOsgiService.getFirstName();
        logger.info("first Name :{}",firstName);
    }
}
