package com.atmdeposit.atm.repository.service;

import com.atmdeposit.atm.model.dto.AtmDepositResponse;
import com.atmdeposit.atm.model.exceptions.AccountException;
import com.atmdeposit.atm.model.exceptions.CardException;
import com.atmdeposit.atm.model.exceptions.FingerPrintException;
import com.atmdeposit.atm.model.exceptions.PersonException;
import io.reactivex.Single;

public interface AtmDepositService {

  public Single<AtmDepositResponse> getInformacionAtm(String documentNumber)
          throws PersonException, AccountException, CardException, FingerPrintException;
}
