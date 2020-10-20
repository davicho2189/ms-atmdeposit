package com.atmdeposit.atm.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ValidAccountResponse {
  private String accountNumber;
}
