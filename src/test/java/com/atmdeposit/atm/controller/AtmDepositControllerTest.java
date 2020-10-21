package com.atmdeposit.atm.controller;

import com.atmdeposit.atm.model.dto.AccountDto;
import com.atmdeposit.atm.model.dto.AtmDepositResponse;
import com.atmdeposit.atm.repository.service.AtmDepositServiceImpl;
import com.google.gson.Gson;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(AtmDepositController.class)
public class AtmDepositControllerTest {

    @MockBean
    private AtmDepositServiceImpl atmDepositServiceImplTest;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }

    @Test
    public void getAtmDeposit() throws Exception {


        List<AccountDto> accountDtos = new ArrayList<>();
        accountDtos.add(new AccountDto("57036064256095XXXX"));

        AtmDepositResponse atmDepositResponse =
                new AtmDepositResponse("Core", accountDtos, 1228.0);

        Gson gson = new Gson();
        String jsonInString = gson.toJson(atmDepositResponse);

        log.info(jsonInString);
        Single<AtmDepositResponse> singleAtmDepositResponse = Single.just(atmDepositResponse);


        mockMvc.perform(
                MockMvcRequestBuilders.post("/atm/deposits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInString))
                .andExpect(status().isOk());
    }
    
    
    @Test
    public void Test() throws Exception  {       

      mockMvc.perform(
          MockMvcRequestBuilders.post("/atm/test"))
          .andExpect(status().isOk());   
    }
    
}