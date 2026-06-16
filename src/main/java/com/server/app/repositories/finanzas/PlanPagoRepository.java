package com.server.app.repositories.finanzas;

import com.server.app.entities.PlanPago;
import com.server.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanPagoRepository extends JpaRepository<PlanPago, Long> {
    List<PlanPago> findByPrestamoId(Long prestamoId);
    List<PlanPago> findByPrestamoUsuario(User user);
}
