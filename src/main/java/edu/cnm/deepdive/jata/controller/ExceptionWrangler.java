package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.service.FleetAlreadyExistsException;
import edu.cnm.deepdive.jata.service.InvalidShipLocationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionWrangler {

@ExceptionHandler(InvalidShipLocationException.class)
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ship placement invalid, either outside the world or intersecting another ship")
public void invalidShipLocation(){};

@ExceptionHandler(FleetAlreadyExistsException.class)
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User has already placed ships")
public void fleetAlreadyExists() {};
}
