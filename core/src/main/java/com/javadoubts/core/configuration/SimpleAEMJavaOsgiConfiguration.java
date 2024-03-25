package com.javadoubts.core.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Simple AEM JAVA Configuration",
        description = "This is Simple AEM JAVA Configuration"
)
public @interface SimpleAEMJavaOsgiConfiguration {

    @AttributeDefinition(
            name="url enpoint",
            description = "This is url endpoint"
    )String getURLEndpoint();

    @AttributeDefinition(
            name="client id",
            description = "This is client id field"
    )String getClientId();
}
