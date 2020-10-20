package com.atmdeposit.atm.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

  private String cardNumber;
  private Boolean active;
}
