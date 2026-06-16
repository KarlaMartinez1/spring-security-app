package com.server.app.mappers;

import com.server.app.dto.finanzas.PrestamoDto;
import com.server.app.dto.response.PrestamoResponse;
import com.server.app.entities.Prestamo;
import com.server.app.entities.User;
import com.server.app.entities.enums.EstadoPrestamo;
import org.springframework.stereotype.Component;

@Component
public class PrestamoMapper {
    public Prestamo toEntity(PrestamoDto request, User usuario) {
        return Prestamo.builder()
                .capitalSolicitado(request.getCapitalSolicitado())
                .tasaInteresAnual(request.getTasaInteresAnual())
                .plazoMeses(request.getPlazoMeses())
                .estado(EstadoPrestamo.PENDIENTE)
                .usuario(usuario)
                .build();
    }

    public PrestamoResponse toResponse(Prestamo prestamo) {
        return PrestamoResponse.builder()
                .id(prestamo.getId())
                .capitalSolicitado(prestamo.getCapitalSolicitado())
                .tasaInteresAnual(prestamo.getTasaInteresAnual())
                .plazoMeses(prestamo.getPlazoMeses())
                .estado(prestamo.getEstado())
                .build();
    }
}
