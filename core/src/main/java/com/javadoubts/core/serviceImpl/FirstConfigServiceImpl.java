package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.Config.FirstConfig;
import com.javadoubts.core.services.FirstConfigService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = FirstConfigService.class)
@Designate(ocd= FirstConfig.class)
public class FirstConfigServiceImpl implements FirstConfigService {
     private FirstConfig config;

    @Activate
    protected void activate(final FirstConfig config){
        this.config = config;
    }


    @Override
    public String getFirstName() {
        return config.getFirstName();
    }

    @Override
    public String getLastName() {
        return config.getLastName();
    }

    @Override
    public String getClientId() {
        return config.getClientId();
    }
}
