package com.atmdeposit.atm.repository.service;

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
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import org.h2.command.dml.MergeUsing.WhenMatched;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@Slf4j
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AtmDepositServiceImplTest {

	private List<Account> accounts;
	private List<Card> cards;
	private Person person;
	private List<AccountDto> accountDtos;
	private FingerPrint fingerPrint;

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
	private Person personFalsa;

	@BeforeAll
	public static void init() {
		log.info("===> @BeforeAll init");
	}

	@BeforeEach
	public void setUp() {
//		log.info("===> @BeforeEach setUp");

		person = new Person(1, "10000000", true, true);
		personFalsa = new Person(1, "10000000", false, true);

		cards = new ArrayList<Card>(Arrays.asList(new Card("1111222233334441", true),
				new Card("1111222233334442", true), new Card("1111222233334443", true)));

		accounts = new ArrayList<Account>(Arrays.asList(new Account(1000.0, "1111222233334441XX"),
				new Account(500.00, "1111222233334442XX"), new Account(1500.00, "1111222233334431XX")));

		accountDtos = new ArrayList<AccountDto>(Arrays.asList(new AccountDto("1111222233334441XX"),
				new AccountDto("1111222233334442XX"), new AccountDto("1111222233334431XX")));

		fingerPrint = new FingerPrint("core", true);
	}
	
//	@Test
//	public void getInformacionAtmTest_stream()
//			throws AccountException, PersonException, FingerPrintException, CardException {
//			
//		List<Account> accountsStream = new ArrayList();
//		List<AccountDto> accountDtoStream = new ArrayList(); 	
//		
//		accountsStream.stream()
//		    .map(ac -> 
//		    accountDtoStream.add(ArgumentMatchers.any(AccountDto.class)))
//				.collect(Collectors.toList());
//	}
	

	@Test
	public void testgetInfomracionSinFirmaPersona()
			throws AccountException, PersonException, FingerPrintException, CardException {
		Person persontteFalse = new Person(1, "10000000", false, true);
		FingerPrint fingerPrintte = new FingerPrint("core", true);

		when(personsClienteRest.getPerson(Mockito.anyString())).thenReturn(persontteFalse);

		when(fingerPrintReniecRest.getFingerPrint(
				ArgumentMatchers.any(FingerPrintRequest.class)))
				.thenReturn(fingerPrintte);
		Single<AtmDepositResponse> objReactivo = atmDepositServiceImplTest.getInformacionAtm("10000000");
		assertEquals(false, persontteFalse.getFingerprint());
	}

	@Test
	public void testgetInfomracionFirmaPersona()
			throws AccountException, PersonException, FingerPrintException, CardException {
		Person persontte = new Person(1, "10000000", true, true);
		FingerPrint fingerPrintte = new FingerPrint("core", true);
		when(personsClienteRest.getPerson(Mockito.anyString())).thenReturn(persontte);
		when(fingerPrintRest.getFingerPrint(ArgumentMatchers.any(FingerPrintRequest.class))).thenReturn(fingerPrintte);
		
		Single<AtmDepositResponse> objReactivo = atmDepositServiceImplTest.getInformacionAtm("10000000");
		assertEquals(true, persontte.getFingerprint());
	}

	@Test
	public void getInformacionAtmTest() throws AccountException, PersonException, FingerPrintException, CardException {

		AtmDepositResponse atmDepositResponse = new AtmDepositResponse();

		lenient().when(atmDepositServiceImplTest.getPerson("10000000")).thenReturn(person);
		lenient().when(atmDepositServiceImplTest.getFingerPrint(person)).thenReturn(fingerPrint);
		lenient().when(atmDepositServiceImplTest.getFingerPrintReniec(person)).thenReturn(fingerPrint);
		atmDepositResponse.setFingerprintEntityName("core");
		atmDepositResponse.setValidAccounts(accountDtos);
		atmDepositResponse.setCustomerAmount(2000.00);
	}

	@Test
	public void getPerson() throws PersonException {
		log.info("==>getPerson");

		Person p = new Person(1, "10000000", true, true);
		log.info("==>" + p.toString());

		when(personsClienteRest.getPerson("10000000")).thenReturn(p);
		Person personTest = atmDepositServiceImplTest.getPerson("10000000");
		assertNotNull(personTest);

	}

	@Test
	void getCardTest() throws CardException {

		assertThat(cards, hasSize(3));
	}

//	@Test
//	public void getAccountsTest() {
//
//		List<Boolean> Result = cards.parallelStream()
//				.map(c -> accounts.add(accountClienteRest.getAccount(c.getCardNumber()))).collect(Collectors.toList());
//		assertEquals(3, Result.size());
//	}


	@Test
	public void getFingerPrintTest() throws Exception {

		Person p = new Person(1, "10000000", true, true);

		lenient().when(fingerPrintRest.getFingerPrint(new FingerPrintRequest("10000000")))
				.thenReturn(new FingerPrint("core", true));
		FingerPrint fingerPrint = new FingerPrint();
		atmDepositServiceImplTest.getFingerPrintReniec(p);

		assertNotNull(fingerPrint);
	}

	@Test
	public void getFingerPrintReniecTest() throws Exception {

		Person p = new Person(1, "10000000", false, true);
		lenient().when(fingerPrintRest.getFingerPrint(new FingerPrintRequest("10000000")))
				.thenReturn(new FingerPrint("core", true));

		FingerPrint fingerPrint = new FingerPrint();
		atmDepositServiceImplTest.getFingerPrintReniec(p);

		assertNotNull(fingerPrint);
	}
	
	@Test
	public void  getPruebasTest() throws Exception{
		//Mockito.anyString() ArgumentMatchers.any(FingerPrintRequest.class)
	
		 List<Account> acc = atmDepositServiceImplTest.getPruebas();
		
	}

}