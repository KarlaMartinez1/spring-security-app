package com.server.app.dto.response;

import com.server.app.entities.enums.EstadoPlanPago;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlanPagoResponse {
    private Long id;
    private Integer numeroCuota;
    private BigDecimal montoCapital;
    private BigDecimal montoInteres;
    private LocalDate fechaVencimiento;
    private EstadoPlanPago estado;
}
