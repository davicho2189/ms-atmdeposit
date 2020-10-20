package com.atmdeposit.atm.repository.service;

import com.atmdeposit.atm.clientes.AccountClienteRest;
import com.atmdeposit.atm.clientes.CardClienteRest;
import com.atmdeposit.atm.clientes.FingerPrintReniecRest;
import com.atmdeposit.atm.clientes.FingerPrintRest;
import com.atmdeposit.atm.clientes.PersonsClienteRest;
import com.atmdeposit.atm.model.dto.AccountDto;
import com.atmdeposit.atm.model.dto.AtmDepositResponse;
import com.atmdeposit.atm.model.dto.FingerPrintRequest;
import com.atmdeposit.atm.model.entity.Account;
import com.atmdeposit.atm.model.entity.Card;
import com.atmdeposit.atm.model.entity.FingerPrint;
import com.atmdeposit.atm.model.entity.Person;
import com.atmdeposit.atm.model.exceptions.AccountException;
import com.atmdeposit.atm.model.exceptions.CardException;
import com.atmdeposit.atm.model.exceptions.FingerPrintException;
import com.atmdeposit.atm.model.exceptions.PersonException;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("AtmDepositServiceFeign")
public class AtmDepositService implements IAtmDepositService {

  @Autowired
  private PersonsClienteRest personsClienteRest;

  @Autowired
  private FingerPrintRest fingerPrintRest;

  @Autowired
  private FingerPrintReniecRest fingerPrintReniecRest;

  @Autowired
  private CardClienteRest cardClienteRest;

  @Autowired
  private AccountClienteRest accountClienteRest;

  /**
   * getInformacionAtm.
   **/
  public Single<AtmDepositResponse> getInformacionAtm(String documentNumber)
      throws PersonException, AccountException, CardException, FingerPrintException {

    AtmDepositResponse atmDepositResponse = new AtmDepositResponse();
    List<AccountDto> accountDtos = new ArrayList<>();

    FingerPrintRequest fingerPrintRequest = new FingerPrintRequest(documentNumber);
    Person person = getPerson(documentNumber);

    FingerPrint fingerPrint = getFingerPrint(fingerPrintRequest, person.getFingerprint());
    List<Card> cards = getCard(documentNumber);
    final List<Account> accounts = getAccounts(cards);
    accounts.stream().map(ac -> accountDtos.add(new AccountDto(ac.getAccountNumber())))
      .collect(Collectors.toList());
    atmDepositResponse.setFingerprintEntityName(fingerPrint.getEntityName());
    atmDepositResponse.setValidAccounts(accountDtos);
    atmDepositResponse.setCustomerAmount(accounts.stream().mapToDouble(x -> x.getAmount()).sum());

    return Single.just(atmDepositResponse).subscribeOn(Schedulers.io());
  }

  public Person getPerson(String documentNumber) throws PersonException {

    return personsClienteRest.getPerson(documentNumber);
  }

  public List<Card> getCard(String documentNumber) throws CardException {
    return cardClienteRest.getCards(documentNumber).stream().filter(x -> x.getActive() == true)
        .collect(Collectors.toList());
  }
  
  /**   
   * getAccounts.
   **/
  public List<Account> getAccounts(List<Card> cards) {

    List<Account> accounts = new ArrayList<Account>();

    Observable.just(cards).flatMapIterable(c -> c)
      .map(c -> accountClienteRest.getAccount(c.getCardNumber()))
        .subscribe(x -> accounts.addAll(x));
    return accounts;

  }

  public FingerPrint getFingerPrint(FingerPrintRequest fingerPrintRequest, Boolean tipo) 
      throws FingerPrintException {
    return tipo ? fingerPrintRest.getFingerPrint(fingerPrintRequest)
        : fingerPrintReniecRest.getFingerPrint(fingerPrintRequest);
  }

}
