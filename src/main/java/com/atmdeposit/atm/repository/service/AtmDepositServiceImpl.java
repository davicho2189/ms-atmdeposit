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
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Scheduler;


@Slf4j
@Service
public class AtmDepositServiceImpl implements AtmDepositService {

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
    
    Person person = getPerson(documentNumber);
    
    FingerPrint fingerPrint = person.getFingerprint() 
        ? getFingerPrint(person) : getFingerPrintReniec(person);

    List<Card> cards = getCard(documentNumber);

    final List<Account> accounts = getAccounts(cards);
    accountDtos = accounts.stream().map(ac -> new AccountDto(ac.getAccountNumber()))   
    .collect(Collectors.toList());

    atmDepositResponse.setFingerprintEntityName(fingerPrint.getEntityName());
    atmDepositResponse.setValidAccounts(accountDtos);
    Double suma = 0.0;
    suma = accounts.stream().mapToDouble(x -> x.getAmount()).sum();
    atmDepositResponse.setCustomerAmount(suma);

    return Single.just(atmDepositResponse).subscribeOn(Schedulers.io());
  }

  public Person getPerson(String documentNumber) throws PersonException {

    return personsClienteRest.getPerson(documentNumber);
  }

  public List<Card> getCard(String documentNumber) throws CardException {
    return cardClienteRest.getCards(documentNumber);
  }

  /**
   * getAccounts.
   **/
  public List<Account> getAccounts(List<Card> cards) {

    log.info("getAccounts--> Inicia");
    List<Account> accounts = new ArrayList<>();

    cards.parallelStream()
            .map(c -> accounts.add(accountClienteRest.getAccount(c.getCardNumber())))
            .collect(Collectors.toList());
    
    log.info("getAccounts-->" + accounts.toString());
     
    return accounts;
  }

  
  /**
   *getFingerPrint.
   ***/
  public FingerPrint getFingerPrint(Person person)
      throws FingerPrintException {
    
    return fingerPrintRest.getFingerPrint(new FingerPrintRequest(person.getDocument()));
  }

  /**
   *getFingerPrintReniec.
   ***/
  public FingerPrint getFingerPrintReniec(Person person)
          throws FingerPrintException {
    
    // personsClienteRest.insertFingerPrint(person.getId());
    
    return fingerPrintReniecRest.getFingerPrint(new FingerPrintRequest(person.getDocument()));

  }
  
  
  @Override
  public List<Account> getPruebas() {

//    List<Account> accounts =
//        new ArrayList<Account>(Arrays.asList(new Account(1000.0, "1111222233334441XX"),
//        new Account(500.00, "1111222233334442XX"), new Account(1500.00, "1111222233334431XX")));
//
//    return accounts;
     log.info("getAccounts--> Inicia");

     List<Card> cards = new ArrayList<>();
     cards.add(new Card("1111222233334441",true));
     cards.add(new Card("1111222233334442",true));
     cards.add(new Card("1111222233334443",true));
     List<Account> accounts = new ArrayList<>();
      //accountClienteRest.getAccount(c.getCardNumber())
     Observable.just(cards)
             .subscribeOn(Schedulers.io())
             .flatMapIterable(x->x)
             .map(c->
                     Single.just(accountClienteRest.getAccount(c.getCardNumber())).subscribeOn(Schedulers.io())
             )
             .subscribe(account -> accounts.add(account.blockingGet()));

    return  accounts;

  }   

  /*
  /**
   * getAccounts.
   **-/
  @Override
  public List<Account> getAccountTests() {

    log.info("getAccounts--> Inicia");
    List<Account> accounts = new ArrayList<>();
    List<Card> cards = new ArrayList<>();
    cards.add(new Card("1111222233334441",true));
    cards.add(new Card("1111222233334442",true));
    cards.add(new Card("1111222233334443",true));
    log.info(cards.toString());
    cards.parallelStream()
            .map(c->accounts.add(accountClienteRest.getAccount(c.getCardNumber())))
            .collect(Collectors.toList());
//    cards.stream().map(x-> Single.just(x)
//            .subscribeOn(Schedulers.newThread())
//            .map(c-> {
//                      log.info(c.getCardNumber());
//                      accounts.add(accountClienteRest.getAccount(c.getCardNumber()));
//
//                              return null;
//                    }
//
//            ).subscribe()).collect(Collectors.toList());
    log.info("getAccounts-->" + accounts.toString());
    return accounts;
  }
  */

}
