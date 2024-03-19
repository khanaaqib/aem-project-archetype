package com.javadoubts.core.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Simple AEM Configuration",
        description = "This is Simple AEM Configuration"
)
public @interface SimpleAEMOsgiConfiguration {

    @AttributeDefinition(
            name="cron job timing",
            description  ="This is cron job timing"
    )String getTime();

    @AttributeDefinition(
            name="scheduler Name",
            description = "This is Scheduler Name"
    )String schedulerName();

}
