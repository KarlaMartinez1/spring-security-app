package com.server.app.dto.response;

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

public class AbonoResponse {
    private Long id;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private BigDecimal recargoMora;}
