package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.TrainingTestingService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.util.Scanner;

@Component(service = TrainingTestingService.class)
public class TrainingTestingServiceImpl implements TrainingTestingService {

    @Override
    public String getUserInfo() {
        File file = new File(
                "C:\\Users\\aaqib\\OneDrive\\Desktop\\schedulerTest.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                return sc.nextLine();
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}
