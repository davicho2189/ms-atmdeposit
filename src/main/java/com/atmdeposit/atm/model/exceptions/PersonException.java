package com.atmdeposit.atm.model.exceptions;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Persona No encontrada")
public class PersonException extends Exception implements Serializable {

  private static final long serialVersionUID = 1L;

  public PersonException() {
  }

}