package fr.diginamic.repositories;


import fr.diginamic.entities.Language;
import fr.diginamic.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends PagingAndSortingRepository<Language, Integer>, CrudRepository<Language,Integer> {
    Language findByNameLanguage(String nameLanguage);

}