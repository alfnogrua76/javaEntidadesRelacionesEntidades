package com.example.demo2Prueba.config;

import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerCustom {//esta se puede llamar como quieras es la clase para manejar las excepciones

//    @ExceptionHandler
//    public ResponseEntity<String> handlerRuntimeException(RuntimeException ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFound(InvalidJpaQueryMethodException ex){
        assert HttpStatus.resolve(303) != null;
        return new ResponseEntity<>(Errores.INTERNAL_ERROR.getDescripcion(), HttpStatus.resolve(303));
    }

    @ExceptionHandler
    public ResponseEntity<Long> handlerRuntimeException(RuntimeException ex){
        assert HttpStatus.resolve(303) != null;
        return new ResponseEntity<>(Errores.INTERNAL_ERROR.getStatus(), HttpStatus.resolve(303));
    }
    
}


