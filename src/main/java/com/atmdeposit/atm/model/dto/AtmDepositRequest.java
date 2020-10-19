package com.atmdeposit.atm.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel("AtmDepositRequest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AtmDepositRequest {

    private String documentNumber;
}