package fr.diginamic.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DataBaseInsertion {

    @Autowired
    ParameterExtracter parameterExtracter;
    public void insertFromFile(){
        insertActeurs() 
    }
    public void insertAceurs() {
        HashMap<Integer,Integer> structureFile = new HashMap<>();
        System.out.println("Lecture Fichier  : " +parameterExtracter.getActeurPath());
        Path acteurPath = Paths.get(parameterExtracter.getActeurPath());
        try{
            Stream<String> lines = Files.lines(acteurPath);
            lines.forEach(line ->{
                int size = line.split(";").length;
                System.out.println(size);
                if(structureFile.get(size) == null){
                    structureFile.put(size,1);
                }else{
                    structureFile.put(size,structureFile.get(size)+1);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

     
	System.out.println(structureFile);
    }
}
