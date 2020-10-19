package com.atmdeposit.atm.model.dto;

import com.atmdeposit.atm.model.entity.Account;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
