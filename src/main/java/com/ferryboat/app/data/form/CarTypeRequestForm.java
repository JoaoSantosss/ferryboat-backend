package com.ferryboat.app.data.form;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CarTypeRequestForm {
    private String carType;
    private Integer space;
    private BigDecimal cost;
}
