package fr.diginamic.controllers;

import fr.diginamic.dto.*;
import fr.diginamic.entities.java.FilmCoupleWithCommonActors;
import fr.diginamic.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    /**
     * @param page
     * @param size
     * @return
     */
    @GetMapping()
    public Page<PersonDto> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return actorService.getALLActors(page, size);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ActorDto getById(@PathVariable int id) {
        return actorService.getActorById(id);
    }

    /**
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/name/{name}")
    public Page<ActorDto> getByName(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return actorService.getActorByName(name, page, size);
    }

    /**
     * @param name
     * @return
     */

    @GetMapping("/auto-complete/{name}")
    public Page<BasicPersonDto> ActorNameAutoComplete(@PathVariable String name) {
        return actorService.getActorsWithNameLike(name);
    }

    /**
     * @param id
     * @param page
     * @param size
     * @return
     */

    @GetMapping("/{id}/films")
    public Page<SimpleFilmDto> getFilmsByActorId(@PathVariable int id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return actorService.getFilmsByIdActor(id, page, size);
    }

    /**
     * @param id
     * @param min
     * @param max
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{id}/films/year{min}{max}")
    public Page<SimpleFilmDto> getFilmsInYearsIntervalByActorId(@PathVariable int id, @RequestParam int min, @RequestParam int max, @PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return actorService.findFilmsActorIdAndYearInterval(id, min, max, page, size);
    }

    /**
     * Extract movie common to 2 given actors or actresses.
     *
     * @param id1
     * @param id2
     * @return the films common to 2 actors or actresses
     */
    @GetMapping("/{id1}/{id2}/films")
    public Page<SimpleFilmDto> getAllFilmInCommonForTwoActors(@PathVariable int id1, @PathVariable int id2,
                                                              @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return actorService.getCommonfilmsForTwoActors(id1, id2, page, size);
    }

    /**
     * @return
     */
    @GetMapping("/quiz/generate")
    public FilmCoupleWithCommonActors getFilmCoupleForQuiz() {
        return actorService.getOneFilmForQuiz();
    }

}
