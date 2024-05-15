package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.configuration.TrainingTestOsgiConfigurationService;
import com.javadoubts.core.services.TrainingTestService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = TrainingTestService.class)
@Designate(ocd= TrainingTestOsgiConfigurationService.class)
public class TrainingTestServiceImpl implements TrainingTestService {

    private String apiEndPoint;

    @Activate
    protected void activate(TrainingTestOsgiConfigurationService configurationService){
        apiEndPoint=configurationService.getAPIEndpoint();
    }

    @Override
    public String fetchOsgiServiceValue() {
        return apiEndPoint;
    }
}
