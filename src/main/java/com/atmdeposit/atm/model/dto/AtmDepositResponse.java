package com.atmdeposit.atm.model.dto;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel("AtmDepositResponse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AtmDepositResponse {

  private String fingerprintEntityName;
  private List<AccountDto> validAccounts;
  private Double customerAmount;

}
