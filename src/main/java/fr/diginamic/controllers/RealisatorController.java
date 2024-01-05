package fr.diginamic.controllers;

import fr.diginamic.dto.*;
import fr.diginamic.entities.Person;
import fr.diginamic.exceptions.AnomalyPersonException;
import fr.diginamic.services.RealisatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realisators")
public class RealisatorController {

    @Autowired
    RealisatorService realisatorService;
    @GetMapping()
    public Page<PersonDto> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return realisatorService.getALLRealisators(page,size);
    }

    @GetMapping("/{id}")
    public RealisatorDto getById(@PathVariable int id){
        return realisatorService.getRealisatorByid(id);
    }

    @GetMapping("/name/{name}")
    public Page<SimplePersonDto> getByName(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20")int size){
        return realisatorService.getRealisatorByName(name,page,size);
    }

    @GetMapping("/auto-complete/{name}")
    public Page<BasicPersonDto> RealisatorNameAutoComple(@PathVariable String name){
        return realisatorService.getRealisatorsWithNameLike(name);
    }

    @GetMapping("/{id}/films")
    public Page<SimpleFilmDto> getRealisationsByRealisatorId(@PathVariable int id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return realisatorService.getRealisationsByid(id,page,size);
    }

    @PostMapping("/{id}")
    public String updateRealisator(@PathVariable int id, @RequestBody PersonDto personUpdated) throws AnomalyPersonException {

        return realisatorService.update(id, personUpdated);
    }

}