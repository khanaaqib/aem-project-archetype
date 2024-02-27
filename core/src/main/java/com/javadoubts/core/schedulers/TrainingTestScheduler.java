package com.javadoubts.core.schedulers;

import com.javadoubts.core.Config.TrainingTestSchedulerConfig;
import com.javadoubts.core.services.TrainingTestingService;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = TrainingTestSchedulerConfig.class)
public class TrainingTestScheduler implements Runnable {

    private Logger log = LoggerFactory.getLogger(TrainingTestScheduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    TrainingTestingService trainingTestingService;

    @Activate
    protected void activate(TrainingTestSchedulerConfig config){
        addScheduler(config);
    }

    public void addScheduler(TrainingTestSchedulerConfig config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronJob());
        scheduleOptions.name(config.getSchedulerName());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    protected void deactivate(TrainingTestSchedulerConfig config){
        removeScheduler(config);
    }

    public void removeScheduler(TrainingTestSchedulerConfig config){
        scheduler.unschedule(config.getSchedulerName());
    }


    @Override
    public void run() {
     log.info("run method running properly with config value");
     String getInfo = trainingTestingService.getUserInfo();
        log.info("to check user info::::{}",getInfo);
    }
}
