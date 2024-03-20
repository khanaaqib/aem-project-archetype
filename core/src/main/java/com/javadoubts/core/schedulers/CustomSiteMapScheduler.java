package com.javadoubts.core.schedulers;


import com.javadoubts.core.configuration.SiteMapConfiguration;
import com.javadoubts.core.services.CustomSiteMapService;
import jdk.vm.ci.code.site.Site;
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

@Component(service = Runnable.class,immediate = true)
@Designate(ocd= SiteMapConfiguration.class)
public class CustomSiteMapScheduler implements Runnable{

    private Logger log = LoggerFactory.getLogger(CustomSiteMapScheduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    CustomSiteMapService service;



    @Activate
    protected void activate(SiteMapConfiguration configuration){
        addScheduler(configuration);
    }


    public void  addScheduler(SiteMapConfiguration configuration){
        ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.getCronJob());
        scheduleOptions.name(configuration.getSchedulerName());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    protected void deactivate(SiteMapConfiguration configuration){
       removeScheduler(configuration);
    }

    public void  removeScheduler(SiteMapConfiguration configuration){
        scheduler.unschedule(configuration.getSchedulerName());
    }

    @Override
    public void run() {
       log.info("inside run method");
        try {
            List<String> resp = service.getPageResult();
            log.info("page path response:{}", resp);
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
