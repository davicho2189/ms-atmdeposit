package com.atmdeposit.atm.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Account")
@Data
@NoArgsConstructor
public class Account {	
	
    private Double amount;   
    private String accountNumber;

}
