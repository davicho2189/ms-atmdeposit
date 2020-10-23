package com.atmdeposit.atm.clientes;

import com.atmdeposit.atm.model.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-accounts")
public interface AccountClienteRest {

  @GetMapping("/core/accounts/{cardNumber}")
  public Account getAccount(@PathVariable String cardNumber);

}
