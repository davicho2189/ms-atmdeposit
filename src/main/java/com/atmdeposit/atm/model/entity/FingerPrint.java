package com.atmdeposit.atm.model.entity;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel("FingerPrint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FingerPrint implements Serializable {

  private static final long serialVersionUID = 1L;

  private String entityName;
  private Boolean success;
}
