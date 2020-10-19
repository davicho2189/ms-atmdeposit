package com.atmdeposit.atm.repository.Service;

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
import com.atmdeposit.atm.model.exceptions.PersonException;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.bouncycastle.util.Fingerprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("AtmDepositServiceFeign")
public class AtmDepositService implements IAtmDepositService{
		
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

	public Single<AtmDepositResponse> GetInformacionAtm(String documentNumber)
			throws PersonException,AccountException, CardException
	{
		
		log.info("Person ->"+documentNumber);
		
		AtmDepositResponse atmDepositResponse = new AtmDepositResponse();
		List<AccountDto> accountDtos = new ArrayList<>();
		
		FingerPrintRequest fingerPrintRequest =  new FingerPrintRequest(documentNumber);
		Single<Person> persons = GetPerson(documentNumber);
//		Single<FingerPrint> fingerPrint = Single.just(GetFingerPrint(fingerPrintRequest,person.getFingerprint());
//
//		Single.just(atmDepositResponse)
//				.map(p -> GetPerson(documentNumber))
//				.flatMap(f->GetFingerPrint(fingerPrintRequest,p))
//				.doOnSubscribe(Schedulers.io())

		return Single.just(atmDepositResponse).subscribeOn(Schedulers.io());
	}

	public Single<Person> GetPerson (String documentNumber) throws PersonException {
			return personsClienteRest.GetPerson(documentNumber);
	}

	public List<Card> GetCard (String documentNumber) throws CardException {
		return cardClienteRest
				.GetCards(documentNumber)
				.stream()
				.filter(x->x.getActive()==true).collect(Collectors.toList());
	}

	public List<Account> GetAccounts (String documentNumber)  {
		return accountClienteRest.GetAccount(documentNumber);
	}

	public Single<FingerPrint> GetFingerPrint (FingerPrintRequest fingerPrintRequest , Boolean tipo) throws PersonException {
		return  tipo ?
				 fingerPrintRest.GetFingerPrint(fingerPrintRequest)
				:fingerPrintReniecRest.GetFingerPrint(fingerPrintRequest);
	}
	
}
