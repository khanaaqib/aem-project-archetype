package com.javadoubts.core.schedulers;

import com.javadoubts.core.Config.FirstSchedulerConfig;
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

@Component(service = FirstScheduler.class)
@Designate(ocd= FirstSchedulerConfig.class)
public class FirstScheduler implements Runnable{

    private Logger log = LoggerFactory.getLogger(FirstScheduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    DemoService demoService;

    @Activate
    protected void activate(final FirstSchedulerConfig config){
        addScheduler(config);
    }

    public void addScheduler(FirstSchedulerConfig config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.getCronJob());
        scheduleOptions.name(config.getSchedulerName());
        scheduler.schedule(this,scheduleOptions);
        log.info("scheduler added successfully");
    }

    @Deactivate
    public  void deactivate(final FirstSchedulerConfig config){
        removeScheduler(config);
    }

    public void removeScheduler(FirstSchedulerConfig config){
        scheduler.unschedule(config.getSchedulerName());
    }


    @Override
    public void run() {
        log.info("Run method triggered");
        String message = demoService.getFileInfo();
        log.info("message info{}",message);
    }
}
