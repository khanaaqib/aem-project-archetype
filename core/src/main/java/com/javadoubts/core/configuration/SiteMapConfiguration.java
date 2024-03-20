package com.javadoubts.core.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name ="Custom Site Map configuration",
        description = "This is Custom site map configuration"
)
public @interface SiteMapConfiguration {

    @AttributeDefinition(
            name="Scheduler cron job",
            description = "This is scheduler cron job field"
    )
    String getCronJob();


    @AttributeDefinition(
            name ="Custom Scheduler Name",
            description = "This is Custom Scheduler field"
    )
    String getSchedulerName();

}
