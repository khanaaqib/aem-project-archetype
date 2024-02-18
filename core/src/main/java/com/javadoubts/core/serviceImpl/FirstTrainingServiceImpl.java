package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.FirstTrainingService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.util.Scanner;

@Component(service = FirstTrainingService.class)
public class FirstTrainingServiceImpl implements FirstTrainingService {

    @Override
    public String getFullName() {
        return "Aaqib Khan";
    }

    @Override
    public String getFileValue() {
        File file = new File(
                "C:\\Users\\aaqib\\OneDrive\\Desktop\\aaqib.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                return sc.nextLine();
        } catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}
