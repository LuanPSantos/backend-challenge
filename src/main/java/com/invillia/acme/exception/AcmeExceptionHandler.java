package com.invillia.acme.exception;

import com.invillia.acme.model.dto.AcmeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class AcmeExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AcmeException handleViolationException(Exception exception) {
        return new AcmeException()
                .setMessage("Ocorreu um erro de validação!")
                .setExceptionType(exception.getClass().getName());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AcmeException handleNoSuchElementException(NoSuchElementException exception) {
        return new AcmeException()
                .setMessage("Elemento procurado não existe!")
                .setExceptionType(exception.getClass().getName());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AcmeException handleException(Exception exception) {
        return new AcmeException()
                .setMessage("Ocorreu um erro inesperado!")
                .setExceptionType(exception.getClass().getName());
    }
}
