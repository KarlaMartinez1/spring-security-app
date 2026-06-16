package com.server.app.controllers;

import com.server.app.dto.finanzas.AbonoDto;
import com.server.app.dto.finanzas.PrestamoDto;
import com.server.app.dto.response.AbonoResponse;
import com.server.app.dto.response.PlanPagoResponse;
import com.server.app.dto.response.PrestamoResponse;
import com.server.app.dto.response.ResumenCreditoResponse;
import com.server.app.entities.User;
import com.server.app.services.AbonoService;
import com.server.app.services.PrestamoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
@RequiredArgsConstructor
public class FinanceController {

    private final PrestamoService prestamoService;
    private final AbonoService abonoService;

    @PostMapping("/prestamos")
    public ResponseEntity<PrestamoResponse> crearPrestamo(
            @Valid @RequestBody PrestamoDto request
    ) {
        User usuario = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                prestamoService.solicitarPrestamo(request, usuario)
        );
    }

    @GetMapping("/prestamos")
    public ResponseEntity<List<PrestamoResponse>> obtenerPrestamos() {

        User usuario = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(
                prestamoService.obtenerHistorialPrestamos(usuario)
        );
    }

    @GetMapping("/prestamos/{id}/planes-pago")
    public ResponseEntity<List<PlanPagoResponse>> obtenerPlanPagos(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(
                prestamoService.obtenerCuotas(id)
        );
    }

    @PostMapping("/abonos")
    public ResponseEntity<AbonoResponse> registrarPago(
            @Valid @RequestBody AbonoDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                abonoService.registrarPago(request)
        );
    }

    @GetMapping("/resumen-credito")
    public ResponseEntity<ResumenCreditoResponse> obtenerResumenCredito() {

        User usuario = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(
                prestamoService.obtenerResumen(usuario)
        );
    }
}