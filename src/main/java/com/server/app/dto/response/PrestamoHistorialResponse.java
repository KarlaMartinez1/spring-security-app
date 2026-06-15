package com.server.app.dto.response;

import com.server.app.entities.enums.EstadoPrestamo;

import java.math.BigDecimal;

public class PrestamoHistorialResponse {
    private Long id;
    private BigDecimal capitalSolicitado;
    private BigDecimal tasaInteresAnual;
    private Integer plazoMeses;
    private EstadoPrestamo estado;
}
