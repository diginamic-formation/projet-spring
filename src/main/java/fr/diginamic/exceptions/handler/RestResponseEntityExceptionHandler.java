package fr.diginamic.exceptions.handler;

import fr.diginamic.exceptions.AnomalyGenreException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({AnomalyGenreException.class})
    public ResponseEntity<String> handleErrors(AnomalyGenreException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
