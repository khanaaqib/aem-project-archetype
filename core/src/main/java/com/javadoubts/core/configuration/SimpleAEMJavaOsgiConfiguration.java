package com.javadoubts.core.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

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
    )String [] getClientId();

    @AttributeDefinition(
            name="Dropdown Label",
            description = "This is Dropdown Label",
            options = {
                    @Option(label = "US",value = "us"),
                    @Option(label = "IN",value = "in"),
                    @Option(label = "FR",value = "fr")
            }
    )String countryDropdown() default "US";

    @AttributeDefinition(
            name="course",
            description = "This is course field",
            type = AttributeType.BOOLEAN
    )boolean  isCourse() default true;



}
