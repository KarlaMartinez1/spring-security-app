package com.server.app.repositories;

import com.server.app.entities.Prestamo;
import com.server.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuario(User usuario);
    Integer countByUsuarioId(int usuarioId);}
