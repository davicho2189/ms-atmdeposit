package com.atmdeposit.atm.controller;

import com.atmdeposit.atm.model.dto.AtmDepositRequest;
import com.atmdeposit.atm.model.dto.AtmDepositResponse;
import com.atmdeposit.atm.repository.service.IAtmDepositService;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  private IAtmDepositService atmDepositService;

  @ApiOperation(value = "Obtener las tarjetas", response = AtmDepositResponse.class)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
      @ApiResponse(code = 404, message = "No se tarjetas") })
  @PostMapping("/deposits")
  public Single<AtmDepositResponse> 
      getCards(@RequestBody AtmDepositRequest atmDepositRequest) throws Exception {
    return atmDepositService.getInformacionAtm(atmDepositRequest.getDocumentNumber());
  }

}