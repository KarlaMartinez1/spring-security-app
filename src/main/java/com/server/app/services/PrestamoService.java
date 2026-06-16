package com.server.app.services;

import com.server.app.dto.finanzas.PrestamoDto;
import com.server.app.dto.response.PlanPagoResponse;
import com.server.app.dto.response.PrestamoResponse;
import com.server.app.dto.response.ResumenCreditoResponse;
import com.server.app.entities.PlanPago;
import com.server.app.entities.Prestamo;
import com.server.app.entities.User;
import com.server.app.entities.enums.EstadoPlanPago;
import com.server.app.mappers.PlanPagoMapper;
import com.server.app.mappers.PrestamoMapper;
import com.server.app.mappers.ResumenCreditoMapper;
import com.server.app.repositories.PlanPagoRepository;
import com.server.app.repositories.PrestamoRepository;
import com.server.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final PlanPagoRepository planPagoRepository;
    private final UserRepository userRepository;

    private final PrestamoMapper prestamoMapper;
    private final PlanPagoMapper planPagoMapper;
    private final ResumenCreditoMapper resumenMapper;

    @Transactional
    public PrestamoResponse solicitarPrestamo(PrestamoDto request, Integer usuarioId) {
        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Prestamo prestamo = prestamoMapper.toEntity(request, usuario);
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        generarTablaAmortizacion(prestamoGuardado);

        return prestamoMapper.toResponse(prestamoGuardado);
    }

    private void generarTablaAmortizacion(Prestamo prestamo) {
        BigDecimal capitalPorCuota = prestamo.getCapitalSolicitado()
                .divide(BigDecimal.valueOf(prestamo.getPlazoMeses()), 2, RoundingMode.HALF_UP);

        BigDecimal interesMensual = prestamo.getCapitalSolicitado()
                .multiply(prestamo.getTasaInteresAnual())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

        List<PlanPago> cuotas = new ArrayList<>();
        LocalDate fechaVencimiento = LocalDate.now().plusMonths(1);

        for (int i = 1; i <= prestamo.getPlazoMeses(); i++) {
            PlanPago cuota = PlanPago.builder()
                    .numeroCuota(i)
                    .montoCapital(capitalPorCuota)
                    .montoInteres(interesMensual)
                    .fechaVencimiento(fechaVencimiento)
                    .estado(EstadoPlanPago.PENDIENTE)
                    .prestamo(prestamo)
                    .build();

            cuotas.add(cuota);
            fechaVencimiento = fechaVencimiento.plusMonths(1);
        }
        planPagoRepository.saveAll(cuotas);
    }

    public List<PlanPagoResponse> obtenerCuotas(Long prestamoId) {
        return planPagoRepository.findByPrestamoId(prestamoId)
                .stream()
                .map(planPagoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ResumenCreditoResponse obtenerResumen(Integer usuarioId) {
        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Integer cantidad = prestamoRepository.countByUsuarioId(usuarioId);

        List<Prestamo> prestamos = prestamoRepository.findByUsuarioId(usuario);
        BigDecimal totalPrestado = prestamos.stream()
                .map(Prestamo::getCapitalSolicitado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<PlanPago> cuotas = planPagoRepository.findByPrestamoUsuario(usuario);
        BigDecimal saldoPendiente = cuotas.stream()
                .filter(cuota -> cuota.getEstado() == EstadoPlanPago.PENDIENTE)
                .map(cuota -> cuota.getMontoCapital().add(cuota.getMontoInteres()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return resumenMapper.toResumenCreditoResponse(cantidad, totalPrestado, saldoPendiente);
    }
}
