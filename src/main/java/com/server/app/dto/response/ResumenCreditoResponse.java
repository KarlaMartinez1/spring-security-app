package com.server.app.dto.response;

import java.math.BigDecimal;

public class ResumenCreditoResponse {
    private BigDecimal totalDeudaOriginal;
    private BigDecimal saldoPendiente;
    private int cuotasAtrasadas;
    private BigDecimal totalMoraAcumulada;
}
