package com.javadoubts.core.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "AEM Training Configuration Service",
        description = "This is AEM Training Configuration Service"
)
public @interface AemtrainingConfigurationService {




    @AttributeDefinition(
            name="Url end point",
            description = "This is url endpoint"
    )
    String getUrlName();


    @AttributeDefinition(
            name="client id",
            description = "this is cliend id field"
    )
    String getClientId();
}
