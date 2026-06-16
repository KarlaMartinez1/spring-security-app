package com.server.app.mappers;

import com.server.app.dto.response.PlanPagoResponse;
import com.server.app.entities.PlanPago;
import org.springframework.stereotype.Component;

@Component
public class PlanPagoMapper {
    public PlanPagoResponse toResponse(PlanPago planPago){
        return PlanPagoResponse.builder()
                .id(planPago.getId())
                .numeroCuota(planPago.getNumeroCuota())
                .montoCapital(planPago.getMontoCapital())
                .montoInteres(planPago.getMontoInteres())
                .fechaVencimiento(planPago.getFechaVencimiento())
                .estado(planPago.getEstado())
                .build();
    }
}
