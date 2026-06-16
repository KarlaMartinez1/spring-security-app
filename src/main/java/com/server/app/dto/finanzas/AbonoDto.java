package com.server.app.dto.finanzas;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AbonoDto {
    private Long planPagoId;
    private BigDecimal monto;
}
