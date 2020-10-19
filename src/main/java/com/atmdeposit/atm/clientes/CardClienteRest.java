package com.atmdeposit.atm.clientes;

import com.atmdeposit.atm.model.entity.Card;
import com.atmdeposit.atm.model.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="servicio-cards")
public interface CardClienteRest {
	
	@GetMapping("/core/cards/{documentNumber}")
	public List<Card> GetCards(@PathVariable String  documentNumber);
	
}
