package com.atmdeposit.atm.clientes;

import com.atmdeposit.atm.model.entity.Account;
import com.atmdeposit.atm.model.entity.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="servicio-accounts")
public interface AccountClienteRest {
	
	@GetMapping("/core/accounts/{cardNumber}")
	public List<Account> GetAccount(@PathVariable String  cardNumber);
	
}
