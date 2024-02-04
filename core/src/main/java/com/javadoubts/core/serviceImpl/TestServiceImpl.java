package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.Config.DemoConfigService;
import com.javadoubts.core.services.TestService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = TestService.class)
@Designate(ocd= DemoConfigService.class)
public class TestServiceImpl implements TestService {

    private  DemoConfigService demoConfig;

    @Activate
    protected void activate(DemoConfigService demoConfig){
        this.demoConfig= demoConfig;
    }

    @Override
    public String getFirstName() {
        return demoConfig.getFirstName();
    }

    @Override
    public String getLastName() {
        return demoConfig.getLastName();
    }

    @Override
    public String[] getEmail() {
        return demoConfig.getEmail();
    }
}
