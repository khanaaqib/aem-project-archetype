package com.javadoubts.core.schedulers;

import com.javadoubts.core.Config.SchedulerJobConfiguration;
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

@Component(service = Runnable.class,immediate = true)
@Designate(ocd= SchedulerJobConfiguration.class)
public class JobScheduler implements Runnable{

    private Logger logger = LoggerFactory.getLogger(JobScheduler.class);

    @Reference
    DemoService demoService;

    @Reference
    Scheduler scheduler;

    @Activate
    protected void activate(SchedulerJobConfiguration schedulerJobConfiguration){
        addScheduler(schedulerJobConfiguration);
    }

    public void addScheduler(SchedulerJobConfiguration schedulerJobConfiguration){
        ScheduleOptions scheduleOptions = scheduler.EXPR(schedulerJobConfiguration.cronJobValue());
        logger.info("scheduleOptions value check{}",scheduleOptions);
        scheduleOptions.name(schedulerJobConfiguration.shedulerNameValue());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    protected void deactive(SchedulerJobConfiguration schedulerJobConfiguration){
        removeScheduler(schedulerJobConfiguration);
    }

    protected void removeScheduler(SchedulerJobConfiguration schedulerJobConfiguration){
        scheduler.unschedule(schedulerJobConfiguration.shedulerNameValue());
    }

    @Override
    public void run() {
        logger.info("run method executed successfully");
        String message = demoService.getFileInfo();
        logger.info("message value check:{}",message);
    }
}
