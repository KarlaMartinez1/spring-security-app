package com.server.app.mappers;

import com.server.app.dto.response.ResumenCreditoResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ResumenCreditoMapper {
    public ResumenCreditoResponse toResumenCreditoResponse(Integer cantidad, BigDecimal total, BigDecimal saldo) {
        return ResumenCreditoResponse.builder()
                .cantidadPrestamos(cantidad != null ? cantidad : 0)
                .totalPrestado(total != null ? total : BigDecimal.ZERO)
                .saldoPendiente(saldo != null ? saldo : BigDecimal.ZERO)
                .build();
    }
}
