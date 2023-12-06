package fr.diginamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import fr.diginamic.database.DataBaseInsertion;

@SpringBootApplication
public class DataBaseFillingApplication {

    @Autowired
    DataBaseInsertion dataBaseInsertion;

    public static void main(String[] args) {
        System.out.println("démargge application DataBaseFillingApplication");
        SpringApplication application = new SpringApplication(DataBaseFillingApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run();
        DataBaseFillingApplication dataBaseApp = context.getBean(DataBaseFillingApplication.class);
        dataBaseApp.startImport();
    }

    private void startImport() {
        dataBaseInsertion.insertFromFile();
    }
}