package com.atmdeposit.atm.clientes;

import com.atmdeposit.atm.model.entity.Person;
import io.reactivex.Single;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="servicio-persons")
public interface PersonsClienteRest {
	
	@GetMapping("/core/persons/{documentNumber}")
	public Single<Person> GetPerson(@PathVariable String  documentNumber);
	
}
