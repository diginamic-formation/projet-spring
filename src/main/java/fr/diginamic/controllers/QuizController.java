package fr.diginamic.controllers;

import fr.diginamic.entities.java.QuizResponseActors;
import fr.diginamic.entities.java.QuizResponseFilms;
import fr.diginamic.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    /**
     * @return
     */
    @GetMapping("/films/generate")
    public QuizResponseFilms getFilmCoupleForQuiz() {
        return quizService.getOneFilmForQuiz();
    }

    /**
     * @return
     */
    @GetMapping("/actors/generate")
    public QuizResponseActors getActorCoupleForQuiz() {
        return quizService.getOneActorForQuiz();
    }

}
