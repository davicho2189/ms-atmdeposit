package com.atmdeposit.atm.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(code = HttpStatus.FAILED_DEPENDENCY, reason = "Cuentas no encontrada")
public class AccountException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public AccountException(){};

}