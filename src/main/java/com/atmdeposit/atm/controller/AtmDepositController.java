package com.atmdeposit.atm.controller;


import com.atmdeposit.atm.model.dto.AtmDepositRequest;
import com.atmdeposit.atm.model.dto.AtmDepositResponse;
import com.atmdeposit.atm.model.entity.Account;
import com.atmdeposit.atm.repository.service.AtmDepositService;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "AtmDeposit microservicio")
@RestController
@RequestMapping("atm")
public class AtmDepositController {

  @Autowired
  private AtmDepositService atmDepositService;

  @ApiOperation(value = "Obtener respuesta del atm", response = AtmDepositResponse.class)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
      @ApiResponse(code = 404, message = "Error al obtener informacion") })
  //@HystrixCommand(fallbackMethod = "getAtmDeposits")
  @PostMapping("/deposits")
  public Single<AtmDepositResponse> 
      getAtmDeposit(@RequestBody AtmDepositRequest atmDepositRequest) throws Exception {
    return atmDepositService.getInformacionAtm(atmDepositRequest.getDocumentNumber());
  }

  //  public Single<AtmDepositResponse>
  //      getAtmDeposits(@RequestBody AtmDepositRequest atmDepositRequest) throws Exception {
  //    return atmDepositService.getInformacionAtm(atmDepositRequest.getDocumentNumber());
  //  }

  @PostMapping("/test")
  public Single<List<Account>> getPrueba() throws Exception {
    return Single.just(atmDepositService.getPruebas());
  }

}
