package fr.diginamic.services;

import fr.diginamic.dto.*;
import fr.diginamic.entities.java.ActorCoupleWithCommonFilms;
import fr.diginamic.entities.java.FilmCoupleWithCommonActors;
import fr.diginamic.entities.java.QuizResponseActors;
import fr.diginamic.entities.java.QuizResponseFilms;
import fr.diginamic.repositories.ActorRepository;
import fr.diginamic.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {


    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FilmService filmService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private FilmRepository filmRepository;

    private static  List<FilmCoupleWithCommonActors> filmsForQuiz;
    private static  List<ActorCoupleWithCommonFilms> actorsForQuiz;



    public void getCoupleOfFilmWithCommonActors(){
        if(filmsForQuiz == null){
            System.out.println("Chargement de la liste");
            List<Object[]> films = filmRepository.getFilmswithCommonActor();
            filmsForQuiz = films.stream().map(FilmCoupleWithCommonActors::new).toList();
            System.out.println(filmsForQuiz.size()+" films to combine");
        }else{
            System.out.println(filmsForQuiz.size()+" films to combine without loading");
        }
    }

    public QuizResponseFilms getOneFilmForQuiz(){
        getCoupleOfFilmWithCommonActors();
        int index = (int) (Math.random()*filmsForQuiz.size());
        return getFilmQuizResponse(filmsForQuiz.get(index));
    }

    private QuizResponseFilms getFilmQuizResponse(FilmCoupleWithCommonActors filmIds) {
        FilmDto film1 = filmService.getFilmById(filmIds.getIdFilm1());
        FilmDto film2 = filmService.getFilmById(filmIds.getIdFilm2());
        List<BasicPersonDto> actors = filmService.getCommonActorsInFilmIds(filmIds.getIdFilm1(), filmIds.getIdFilm2());
        return new QuizResponseFilms(film1, film2, actors);
    }


    public void getCoupleOfActorWithCommonFilms(){
        if(actorsForQuiz == null){
            System.out.println("Chargement de la liste");
            List<Object[]> actors = actorRepository.getActorswithCommonFilm();
            actorsForQuiz = actors.stream().map(ActorCoupleWithCommonFilms::new).toList();
            System.out.println(actorsForQuiz.size()+" Actors to combine");
        }else{
            System.out.println(actorsForQuiz.size()+" Actors to combine without loading");
        }
    }

    public QuizResponseActors getOneActorForQuiz(){
        getCoupleOfActorWithCommonFilms();
        int index = (int) (Math.random()*actorsForQuiz.size());
        return getActorQuizResponse(actorsForQuiz.get(index));
    }

    private QuizResponseActors getActorQuizResponse(ActorCoupleWithCommonFilms actorIds) {
        ActorDto actor1 = actorService.getActorById(actorIds.getIdPerson1());
        ActorDto actor2 = actorService.getActorById(actorIds.getIdPerson2());
        List<BasicFilmDto> films = actorService.getCommonfilmsForTwoActors(actorIds.getIdPerson1(), actorIds.getIdPerson2());
        return new QuizResponseActors(actor1, actor2, films);
    }
}
