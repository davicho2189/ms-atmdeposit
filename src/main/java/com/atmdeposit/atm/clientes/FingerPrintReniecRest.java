package com.atmdeposit.atm.clientes;

import com.atmdeposit.atm.model.dto.FingerPrintRequest;
import com.atmdeposit.atm.model.entity.FingerPrint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servicio-fingerprintsReniec")
public interface FingerPrintReniecRest {

  @PostMapping("/external/reniec/validate")
  public FingerPrint getFingerPrint(@RequestBody FingerPrintRequest fingerPrint);

}
