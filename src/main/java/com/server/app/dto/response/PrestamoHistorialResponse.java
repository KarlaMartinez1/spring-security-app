package com.server.app.dto.response;

import com.server.app.entities.enums.EstadoPrestamo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PrestamoHistorialResponse {
    private Long id;
    private BigDecimal capitalSolicitado;
    private BigDecimal tasaInteresAnual;
    private Integer plazoMeses;
    private EstadoPrestamo estado;
}
