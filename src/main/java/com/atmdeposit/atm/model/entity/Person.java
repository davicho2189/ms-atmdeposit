package com.atmdeposit.atm.model.entity;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String document;
  private Boolean fingerprint;
  private Boolean blacklist;

}
