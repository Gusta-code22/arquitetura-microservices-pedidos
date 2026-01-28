package github.io.Gusta_code22.common;

import github.io.Gusta_code22.exception.MoedaNaoSuportadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MoedaNaoSuportadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMoedaNaoSuportada(MoedaNaoSuportadaException ex){
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}
