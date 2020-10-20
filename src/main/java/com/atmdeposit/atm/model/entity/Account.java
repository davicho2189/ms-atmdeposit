package com.atmdeposit.atm.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {	
	
    private Double amount;   
    private String accountNumber;

}
