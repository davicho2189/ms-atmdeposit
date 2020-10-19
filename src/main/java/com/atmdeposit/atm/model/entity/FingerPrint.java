package com.atmdeposit.atm.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("FingerPrint")
@Data
@NoArgsConstructor
public class FingerPrint  implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String entityName;
    private  Boolean success;
}
