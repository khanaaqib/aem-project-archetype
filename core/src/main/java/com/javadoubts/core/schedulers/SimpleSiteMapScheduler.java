package com.javadoubts.core.schedulers;

import com.javadoubts.core.configuration.SimpleAEMOsgiConfiguration;
import com.javadoubts.core.services.SimpleSiteMapService;
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

import java.util.List;

@Component(service = Runnable.class,immediate = true)
@Designate(ocd= SimpleAEMOsgiConfiguration.class)
public class SimpleSiteMapScheduler implements Runnable{

    private Logger logger = LoggerFactory.getLogger(SimpleSiteMapScheduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    SimpleSiteMapService service;

    @Activate
    protected  void activate(SimpleAEMOsgiConfiguration configuration){
        addSchduler(configuration);
    }

    //to read the osgi configuration value
    //will scheduler the scheduler job
    protected void addSchduler(SimpleAEMOsgiConfiguration configuration){
        ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.getTime());
        scheduleOptions.name(configuration.schedulerName());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    protected void deactivate(SimpleAEMOsgiConfiguration configuration){
        removeScheduler(configuration);
    }

    protected  void removeScheduler(SimpleAEMOsgiConfiguration configuration){
        scheduler.unschedule(configuration.schedulerName());
    }

    @Override
    public void run() {
       logger.info("inside run method aaqib Test");
        try {
            List<String> response = service.getResult();
            logger.info("response value check for simple site map scheduler:::{}",response);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }
}
