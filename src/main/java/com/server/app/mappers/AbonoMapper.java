package com.server.app.mappers;

import com.server.app.dto.finanzas.AbonoDto;
import com.server.app.dto.response.AbonoResponse;
import com.server.app.entities.Abono;
import com.server.app.entities.PlanPago;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AbonoMapper {public Abono toEntity(AbonoDto request, PlanPago planPago) {
    return Abono.builder()
            .monto(request.getMonto())
            .fechaPago(LocalDate.now())
            .planPago(planPago)
            .build();
}

    public AbonoResponse toResponse(Abono abono) {
        return AbonoResponse.builder()
                .id(abono.getId())
                .monto(abono.getMonto())
                .fechaPago(abono.getFechaPago())
                .recargoMora(abono.getRecargoMora())
                .build();
    }
}
