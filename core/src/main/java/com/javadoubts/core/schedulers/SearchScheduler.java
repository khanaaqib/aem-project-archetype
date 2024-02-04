package com.javadoubts.core.schedulers;

import com.javadoubts.core.Config.SearchSchedulerConfiguration;
import com.javadoubts.core.services.FetchResourceService;
import com.javadoubts.core.services.QueryBuilderSearchService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONArray;
import org.json.JSONException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;

@Component(service = Runnable.class,immediate = true)
@Designate(ocd= SearchSchedulerConfiguration.class)
public class SearchScheduler implements Runnable{

    private Logger logger = LoggerFactory.getLogger(SearchScheduler.class);

    @Reference
    Scheduler scheduler;

    @Reference
    FetchResourceService fetchResourceService;

    @Reference
    QueryBuilderSearchService queryBuilderSearchService;

    @Activate
    protected void activate(SearchSchedulerConfiguration configuration){
        addScheduler(configuration);
    }

    public void addScheduler(SearchSchedulerConfiguration configuration){
        ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.cronJob());
        scheduleOptions.name(configuration.schedulerName());
        scheduler.schedule(this,scheduleOptions);
    }

    @Deactivate
    protected void deactivate(SearchSchedulerConfiguration configuration){
        removeSchelder(configuration);
    }

    public void removeSchelder(SearchSchedulerConfiguration configuration){
        scheduler.unschedule(configuration.schedulerName());
    }

    @Override
    public void run() {
        try {
            ResourceResolver resourceResolver = fetchResourceService.getResourceResolver();
            JSONArray jsonArray = queryBuilderSearchService.getSearchResult(resourceResolver);
            logger.info("JSON ARRAY VALUE CHEKC:----->{}",jsonArray);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }
}
