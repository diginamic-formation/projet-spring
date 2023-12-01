package fr.diginamic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.repositories.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

}
