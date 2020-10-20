package com.atmdeposit.atm.clientes;

import com.atmdeposit.atm.model.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-persons")
public interface PersonsClienteRest {

  @GetMapping("/core/persons/{documentNumber}")
  public Person getPerson(@PathVariable String documentNumber);
  
  @GetMapping("/core/persons/updateFingerPerson/{id}")
  public void insertFingerPrint(@PathVariable Integer id);

}
