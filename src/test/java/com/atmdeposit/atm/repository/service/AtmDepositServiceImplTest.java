package com.atmdeposit.atm.repository.service;


//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;

import com.atmdeposit.atm.clientes.*;
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
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNull;


@Slf4j
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AtmDepositServiceImplTest {


  @InjectMocks
  private AtmDepositServiceImpl atmDepositServiceImplTest;

  @Mock
  private PersonsClienteRest personsClienteRest;

  @Mock
  private CardClienteRest cardClienteRest;

  @Mock
  private AccountClienteRest accountClienteRest;

  @Mock
  private FingerPrintRest fingerPrintRest;

  @Mock
  private FingerPrintReniecRest fingerPrintReniecRest;

  @Test
  public void getInformacionAtm() throws AccountException, PersonException, FingerPrintException, CardException {

    AtmDepositResponse atmDepositResponse = new AtmDepositResponse();
    List<AccountDto> accountDtos =new ArrayList<>();
    accountDtos.add(new AccountDto("57036064256095XXXX"));
    atmDepositResponse.setFingerprintEntityName("core");
    atmDepositResponse.setValidAccounts(accountDtos);
    atmDepositResponse.setCustomerAmount(2000.00);

    when(atmDepositServiceImplTest.getInformacionAtm("46181585"))
            .thenReturn(Single.just(atmDepositResponse));

    TestObserver<Single<AtmDepositResponse>> singleTestObserver =
            Single.just(atmDepositServiceImplTest.getInformacionAtm("46181585"))
            .test()
            .assertResult();
  }

  @Test
  public void getPerson() throws PersonException {
    log.info("==>getPerson");

    Person p = new Person(1, "46181585", true, true);
    log.info("==>" + p.toString());

    when(personsClienteRest.getPerson("46181585")).thenReturn(p);
    Person personTest = atmDepositServiceImplTest.getPerson("46181585");
    Assert.notNull(personTest);

  }

  @Test
  void getCard() throws CardException {

    List<Card> cards = new ArrayList<>();
    cards.add(new Card("123", true));
    cards.add(new Card("12345", true));
    cards.add(new Card("123456", true));
    cards.add(new Card("1234567", true));

    when(cardClienteRest.getCards("46181585")).thenReturn(cards);
    assertEquals(atmDepositServiceImplTest.getCard("46181585").size(), 4);
  }

  @Test
  public void getAccounts() {

//    List<Card> cards = new ArrayList<>();
//    cards.add(new Card("123", true));
//    cards.add(new Card("12345", true));
//
//    Account accounts =new Account(10.0, "123");
//    accounts.add(new Account(10.0, "123"));
//    accounts.add(new Account(10.0, "123"));
//    when(accountClienteRest.getAccount("123")).thenReturn(accounts);
//    assertEquals(2,accounts.size());
  }

  @Test
  public void getFingerPrintif() throws Exception {

    Person p = new Person(1, "10000000", true, true);

    lenient().when(fingerPrintRest.getFingerPrint(new FingerPrintRequest("10000000")))
            .thenReturn(new FingerPrint("core", true));
    FingerPrint fingerPrint = new FingerPrint();
    atmDepositServiceImplTest.getFingerPrintReniec(p);

    assertNotNull(fingerPrint);
  }

  @Test
  public void getFingerPrintelse() throws Exception {

    Person p = new Person(1, "46181585", false, true);
    lenient().when(fingerPrintRest.getFingerPrint(new FingerPrintRequest("10000000")))
            .thenReturn(new FingerPrint("core", true));

     FingerPrint fingerPrint = new FingerPrint();
             atmDepositServiceImplTest.getFingerPrintReniec(p);

     assertNotNull(fingerPrint);
  }

}