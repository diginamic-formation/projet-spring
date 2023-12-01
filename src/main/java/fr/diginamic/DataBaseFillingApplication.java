package fr.diginamic;

import fr.diginamic.database.DataBaseInsertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataBaseFillingApplication implements CommandLineRunner {

    @Autowired
    DataBaseInsertion dataBaseInsertion;
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DataBaseFillingApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
    @Override
    public void run(String... args) throws Exception {
        dataBaseInsertion.insertFromFile();
    }
}
