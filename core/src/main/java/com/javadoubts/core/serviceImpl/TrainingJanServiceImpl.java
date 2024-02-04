package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.TrainingJanService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component(service = TrainingJanService.class)
public class TrainingJanServiceImpl implements TrainingJanService {

    @Override
    public String getuserInfo() {
        File file = new File(
                "C:\\Users\\aaqib\\OneDrive\\Desktop\\aaqib.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                return sc.nextLine();
        }
        catch (FileNotFoundException e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public String getuserDetailInfo() {
        return null;
    }
}
