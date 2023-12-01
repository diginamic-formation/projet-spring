package fr.diginamic.repositories;


import fr.diginamic.entities.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {


    List<Language> findByNameLanguage(String nameLanguage);



}