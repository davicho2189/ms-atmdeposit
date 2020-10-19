package com.atmdeposit.atm.clientes;

import com.atmdeposit.atm.model.dto.FingerPrintRequest;
import com.atmdeposit.atm.model.entity.FingerPrint;
import com.atmdeposit.atm.model.entity.Person;
import io.reactivex.Single;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="servicio-fingerprints")
public interface FingerPrintRest {	
	
	@PostMapping("/core/fingerprints/validate")
	public Single<FingerPrint>   GetFingerPrint(@RequestBody FingerPrintRequest fingerprint);
	
}
