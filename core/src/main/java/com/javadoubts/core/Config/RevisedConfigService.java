package com.javadoubts.core.Config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name="Revised Config",
        description = "THis is revised config"
)
public @interface RevisedConfigService {
    @AttributeDefinition(
            name = "client id",
            description = "this is client id field"
    )
    String getClientId();

    @AttributeDefinition(
            name = "cient secret",
            description = "this is client secret"
    )
    String getClientSecret();
}
