package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.service.InvalidShipLocationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionWrangler {

@ExceptionHandler(InvalidShipLocationException.class)
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ship placement invalid")
public void invalidShipLocation(){};

}