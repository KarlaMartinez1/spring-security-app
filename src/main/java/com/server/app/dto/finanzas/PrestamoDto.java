package com.server.app.dto.finanzas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrestamoDto {
    @NotNull(message = "The requested capital is mandatory")
    @Positive(message = "The requested capital must be a positive value")
    private BigDecimal capitalSolicitado;

    @NotNull(message = "The annual interest rate is mandatory")
    @Positive(message = "The annual interest rate must be a positive value")
    private BigDecimal tasaInteresAnual;

    @NotNull(message = "The term in months is mandatory")
    @Positive(message = "The term in months must be a positive value")
    private Integer plazoMeses;
}
