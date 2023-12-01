package fr.diginamic.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.services.FilmService;

@RestController
@RequestMapping
public class FilmControleur {
	@Autowired
	private FilmService filmService;
}
