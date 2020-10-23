package com.atmdeposit.atm;

import com.atmdeposit.atm.repository.service.AtmDepositServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MsAtmdepositApplicationTests {

  @InjectMocks
  private AtmDepositServiceImpl atmDepositServiceImplTest;

  @Test
  public void contextLoads() {

  }

}
