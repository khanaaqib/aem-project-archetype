package com.javadoubts.core.serviceImpl;

import com.javadoubts.core.services.JanuaryComponentService;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


@Component(service = JanuaryComponentService.class)
public class JanuaryComponentServiceImpl implements JanuaryComponentService {

    @Override
    public String getFirstName() {
        // pass the path to the file as a parameter
        File file = new File(
                "C:\\Users\\aaqib\\Downloads\\test.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
               return sc.nextLine();
        }catch(FileNotFoundException e){
            e.getMessage();
        }
      return null;
    }
}
