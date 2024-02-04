package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.Config.BasicOsgiConfigurationService;
import com.javadoubts.core.services.BasicNewOsgiService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = BasicNewOsgiService.class)
@Designate(ocd = BasicOsgiConfigurationService.class)
public class BasicNewOsgiServiceImpl implements BasicNewOsgiService {

     private BasicOsgiConfigurationService basicOsgiConfigurationService;
    @Activate
    protected void activate(final BasicOsgiConfigurationService basicOsgiConfigurationService){
             this.basicOsgiConfigurationService = basicOsgiConfigurationService;
    }

    @Override
    public String getUrlEndPoint() {
        return this.basicOsgiConfigurationService.getUrlEndPoint();
    }

    @Override
    public String password() {
        return this.basicOsgiConfigurationService.password();
    }
}
