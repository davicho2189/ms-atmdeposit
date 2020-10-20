package com.atmdeposit.atm.controller;

import com.atmdeposit.atm.model.dto.AccountDto;
import com.atmdeposit.atm.model.dto.AtmDepositResponse;
import com.atmdeposit.atm.model.exceptions.AccountException;
import com.atmdeposit.atm.model.exceptions.CardException;
import com.atmdeposit.atm.model.exceptions.FingerPrintException;
import com.atmdeposit.atm.model.exceptions.PersonException;
import com.atmdeposit.atm.repository.service.AtmDepositServiceImpl;
import io.reactivex.Single;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtmDepositController.class)
public class AtmDepositControllerTest {

    @MockBean
    private AtmDepositServiceImpl atmDepositServiceImplTest;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAtmDeposit() throws Exception {


        List<AccountDto> accountDtos =new ArrayList<>();
        accountDtos.add(new AccountDto("57036064256095XXXX"));

        AtmDepositResponse atmDepositResponse =
                new AtmDepositResponse("Core",accountDtos,1228.0);
        Single<AtmDepositResponse> singleAtmDepositResponse = Single.just(atmDepositResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/deposits")
                .accept(MediaType.APPLICATION_JSON);;

        /* Test & Asserts */
        mockMvc
                .perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{ fingerprintEntityName : Core,validAccounts:[{accountNumber:57036064256095XXXX}],customerAmount:1228}", false))
                .andReturn();

    }


}