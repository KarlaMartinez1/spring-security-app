package com.server.app.entities;

import com.server.app.entities.enums.EstadoPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "prestamos")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capital_solicitado")
    private BigDecimal capitalSolicitado;

    @Column(name = "tasa_interes_anual")
    private BigDecimal tasaInteresAnual;

    @Column(name = "plazo_meses")
    private Integer plazoMeses;

    @Column
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User usuario;
}
