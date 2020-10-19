package com.atmdeposit.atm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel("AccountDto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {	
	
 //   private Double amount;   
    private String accountNumber;
}
