package com.atmdeposit.atm.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Card")
@Data
@NoArgsConstructor
public class Card {

    private String cardNumber;
    private Boolean active;
}
