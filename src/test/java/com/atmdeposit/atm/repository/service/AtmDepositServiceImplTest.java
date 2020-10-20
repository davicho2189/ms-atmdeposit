package com.atmdeposit.atm.repository.service;


//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;

import com.atmdeposit.atm.model.dto.AccountDto;
import com.atmdeposit.atm.model.dto.AtmDepositResponse;
import com.atmdeposit.atm.model.entity.Account;
import com.atmdeposit.atm.model.entity.Card;
import com.atmdeposit.atm.model.entity.FingerPrint;
import com.atmdeposit.atm.model.entity.Person;
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
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@Slf4j
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AtmDepositServiceImplTest {


  @InjectMocks
  private AtmDepositServiceImpl atmDepositServiceImplTest;
  
//  @Before
//  public void setUp() throws Exception {
//      MockitoAnnotations.initMocks(this);
//  }
  

  @Test
  public void getInformacionAtm() {
    
    AtmDepositResponse atmDepositResponse = new AtmDepositResponse();
    List<AccountDto> accountDtos =new ArrayList<>();
    accountDtos.add(new AccountDto("57036064256095XXXX"));
    atmDepositResponse.setFingerprintEntityName("core");
    atmDepositResponse.setValidAccounts(accountDtos);
    atmDepositResponse.setCustomerAmount(2000.00);
    
//    when(atmDepositServiceImplTest.getInformacionAtm("46181585")).thenReturn(Single.just(atmDepositResponse));
//    
//    
//    atmDepositServiceImplTest.getInformacionAtm("46181585")
//    .test()
//    .assertComplete()
//    .assertNoErrors()
//    .awaitTerminalEvent();
//    
//   // assertEquals(atmDepositServiceImplTest.getCard("46181585").size(), 4);
    
  }

  @Test
  public void getPerson() throws PersonException {
    log.info("==>getPerson");

//    Person p = new Person(1, "46181585", true, true);
//    log.info("==>" + p.toString());
//
//    Mockito.when(atmDepositServiceImplTest.getPerson("46181585")).thenReturn(p);
//    Person personTest = atmDepositServiceImplTest.getPerson("46181585");
//    // log.info("==>" + person.toString());
//    Assert.notNull(personTest);

  }

  @Test
  void getCard() throws CardException {
    
    List<Card> cards = new ArrayList<>();
    cards.add(new Card("123", true));
    cards.add(new Card("12345", true));
    cards.add(new Card("123456", true));
    cards.add(new Card("1234567", true));
    
    when(atmDepositServiceImplTest.getCard("46181585")).thenReturn(cards);
    assertEquals(atmDepositServiceImplTest.getCard("46181585").size(), 4);
  }

  @Test
  public void getAccounts() {
    
    List<Card> cards = new ArrayList<>();
    cards.add(new Card("123", true));
    cards.add(new Card("12345", true));
    
    List<Account> accounts = new ArrayList<>();
    accounts.add(new Account(10.0, "123"));
    accounts.add(new Account(10.0, "123"));
//    accounts.add(new Account(10.0,"12345"));
//    accounts.add(new Account(10.0,"12345"));
//    accounts.add(new Account(10.0,"12345"));
    
    when(atmDepositServiceImplTest.getAccounts(cards)).thenReturn(accounts);
    assertEquals(accounts.size(), 4);
    
    
  }

  @Test
  public void getFingerPrint() throws Exception {
    
    Person personTrue = new Person(1, "46181585", true, true);
    //Person personFalse = new Person(2, "46181585", false, true);
    
    FingerPrint finger = new FingerPrint("core", true);
    when(atmDepositServiceImplTest.getFingerPrint(personTrue)).thenReturn(finger);
    assertEquals(true, personTrue.getFingerprint());
    
  }
}