package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.Config.TrainingConfigService;
import com.javadoubts.core.services.TrainingService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = TrainingService.class)
@Designate(ocd= TrainingConfigService.class)
public class TrainingServiceImpl implements TrainingService {

    private  TrainingConfigService config;
    @Activate
    protected void activate(final TrainingConfigService config){
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
    public String[] getPhoneNumber() {
        return config.getPhoneNumber();
    }
}
