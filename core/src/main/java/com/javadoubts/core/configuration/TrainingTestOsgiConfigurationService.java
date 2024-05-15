package com.javadoubts.core.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name="Training Test Osgi Configuration Service",
        description = "This is Training Test Osgi Configuration Service"
)
public @interface TrainingTestOsgiConfigurationService {

    @AttributeDefinition(
            name="API End Point",
            description = "This is API End Point"
    )String getAPIEndpoint();

    @AttributeDefinition(
            name="Host Detail",
            description = "This is host field"
    )String getHostName();
}
