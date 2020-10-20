package com.atmdeposit.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
public class MsAtmdepositApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsAtmdepositApplication.class, args);
  }

}
