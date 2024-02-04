package com.javadoubts.core.Config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "First Scheduler",
        description = "This is our first scheduler configuration"
)
public @interface FirstSchedulerConfig {
    @AttributeDefinition(
            name = "Scheduler name",
            description = "Scheduler name field"
    )
    String getSchedulerName();


    @AttributeDefinition(
            name="Crob job ",
            description = "crob job field"
    )
    String getCronJob();
}
