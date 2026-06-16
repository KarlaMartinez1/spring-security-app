package com.server.app.dto.response;

import com.server.app.entities.enums.EstadoPlanPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PlanPagoResponse {
    private Long id;
    private Integer numeroCuota;
    private BigDecimal montoCapital;
    private BigDecimal montoInteres;
    private LocalDate fechaVencimiento;
    private EstadoPlanPago estado;
}
