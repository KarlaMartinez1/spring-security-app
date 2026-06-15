package com.server.app.dto.response;

import com.server.app.entities.enums.EstadoPrestamo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrestamoResponse {
    private Long id;
    private BigDecimal capitalSolicitado;
    private Integer plazoMeses;
    private EstadoPrestamo estado;
}
