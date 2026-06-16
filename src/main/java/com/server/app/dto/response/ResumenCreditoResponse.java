package com.server.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResumenCreditoResponse {
    private Integer cantidadPrestamos;
    private BigDecimal totalPrestado;
    private BigDecimal saldoPendiente;
}
