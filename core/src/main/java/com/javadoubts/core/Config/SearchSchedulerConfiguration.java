package com.javadoubts.core.Config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Search Scheduler Configuration",
        description = "Search Scheduler Configuration field"
)
public @interface SearchSchedulerConfiguration {

    @AttributeDefinition(
            name="cron job",
            description = "con job desc"
    )
    String cronJob();

    @AttributeDefinition(
            name="Scheduler name",
            description = "scheduler Name field"
    )
    String schedulerName();

    @AttributeDefinition(
            name ="api endpoint",
            description = "this is the api endpoint field"
    )
    String getUrlEndpoint();
}
