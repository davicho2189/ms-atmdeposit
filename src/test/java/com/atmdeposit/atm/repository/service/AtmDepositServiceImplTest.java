package com.atmdeposit.atm.repository.service;


import com.atmdeposit.atm.model.entity.Person;
import com.atmdeposit.atm.model.exceptions.PersonException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static org.mockito.Mockito.when;


@Slf4j
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AtmDepositServiceImplTest {

//    @Mock
//    private AuthorRepository authorRepository;
     @InjectMocks
     private AtmDepositServiceImpl atmDepositServiceImplTest;

    @Test
    void getInformacionAtm() {
    }

    @Test
    public void getPerson() throws PersonException {
        log.info("==>getPerson");

        Person p = new Person(1,"46181585",true,true);
        log.info("==>" + p.toString());

        when(atmDepositServiceImplTest.getPerson("46181585")).thenReturn(p);
        Person personTest = atmDepositServiceImplTest.getPerson("46181585");
       // log.info("==>" + person.toString());
        Assert.notNull(personTest);

       }

    @Test
    void getCard() {
    }

    @Test
    void getAccounts() {
    }

    @Test
    void getFingerPrint() {
    }
}