package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.configuration.AemtrainingConfigurationService;
import com.javadoubts.core.services.AemTrainingService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = AemTrainingService.class)
@Designate(ocd= AemtrainingConfigurationService.class)
public class AemTrainingServiceImpl implements AemTrainingService {

    private AemtrainingConfigurationService configurationService;

    @Activate
    protected void activate(AemtrainingConfigurationService service){
        configurationService = service;
    }

    @Override
    public String getURl() {
        return configurationService.getUrlName();
    }

    @Override
    public String getClient() {
        return configurationService.getClientId();
    }
}
