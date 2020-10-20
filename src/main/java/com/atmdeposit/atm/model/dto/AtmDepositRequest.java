package com.atmdeposit.atm.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel("AtmDepositRequest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AtmDepositRequest {

  private String documentNumber;
}