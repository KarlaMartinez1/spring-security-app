package com.server.app.services;

import com.server.app.dto.finanzas.AbonoDto;
import com.server.app.dto.response.AbonoResponse;
import com.server.app.entities.Abono;
import com.server.app.entities.PlanPago;
import com.server.app.entities.enums.EstadoPlanPago;
import com.server.app.mappers.AbonoMapper;
import com.server.app.repositories.AbonoRepository;
import com.server.app.repositories.PlanPagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional

public class AbonoService {

    private final AbonoRepository abonoRepository;
    private final PlanPagoRepository planPagoRepository;
    private final AbonoMapper abonoMapper;

    @Transactional
    public AbonoResponse registrarPago(AbonoDto request) {

        PlanPago cuota = planPagoRepository.findById(request.getPlanPagoId())
                .orElseThrow(() -> new RuntimeException("Cuota no encontrada"));

        if (cuota.getEstado() == EstadoPlanPago.PAGADO) {
            throw new RuntimeException("Esta cuota ya se encuentra pagada");
        }

        Abono abono = abonoMapper.toEntity(request, cuota);

        if (LocalDate.now().isAfter(cuota.getFechaVencimiento())) {
            abono.setRecargoMora(new BigDecimal("5.00"));
        } else {
            abono.setRecargoMora(BigDecimal.ZERO);
        }

        Abono abonoGuardado = abonoRepository.save(abono);

        cuota.setEstado(EstadoPlanPago.PAGADO);
        planPagoRepository.save(cuota);

        return abonoMapper.toResponse(abonoGuardado);
    }
}
