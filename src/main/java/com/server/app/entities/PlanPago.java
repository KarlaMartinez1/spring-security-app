package com.server.app.entities;


import com.server.app.entities.enums.EstadoPlanPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "plan_pago")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PlanPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "monto_capital")
    private BigDecimal montoCapital;

    @Column(name = "monto_interes")
    private BigDecimal montoInteres;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column
    @Enumerated(EnumType.STRING)
    private EstadoPlanPago estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;
}
