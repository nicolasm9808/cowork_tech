package com.cowork_tech.cowork_tech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cowork_tech.cowork_tech.entities.Espacio;
import com.cowork_tech.cowork_tech.entities.EstadoReserva;
import com.cowork_tech.cowork_tech.entities.Reserva;
import com.cowork_tech.cowork_tech.entities.ReservaDTO;
import com.cowork_tech.cowork_tech.entities.TipoEspacio;
import com.cowork_tech.cowork_tech.entities.Usuario;
import com.cowork_tech.cowork_tech.repositories.ReservaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Transactional
    public ResponseEntity<Reserva> crearReserva(Usuario usuario, Espacio espacio,ReservaDTO reservaDTO) {
        if (!espacio.getTipoEspacio().equals(TipoEspacio.ESCRITORIOCOMPARTIDO)) {
            boolean existeConflicto = reservaRepository.existsByEspacioAndFechaReservaAndHoraInicioLessThanAndHoraFinGreaterThan(
                espacio, reservaDTO.getFechaReserva(), reservaDTO.getHoraFin(), reservaDTO.getHoraInicio()
            );

            if (existeConflicto) {
                throw new IllegalArgumentException("El espacio ya está reservado en este horario.");
            }
        }
        Reserva reservaOpt = new Reserva();
        reservaOpt.setUsuario(usuario);
        reservaOpt.setEspacio(espacio);
        reservaOpt.setFechaReserva(reservaDTO.getFechaReserva());
        reservaOpt.setHoraInicio(reservaDTO.getHoraInicio());
        reservaOpt.setHoraFin(reservaDTO.getHoraFin());
        reservaOpt.setEstadoReserva(reservaDTO.getEstadoReserva());

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reservaOpt));
    }

    public List<Reserva> reservasActivas() {
        return reservaRepository.findByEstadoReserva(EstadoReserva.PENDIENTE);
    }

    @Transactional
    public Reserva actualizarReserva(Long id, ReservaDTO reservaDTO) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        if (reservaOpt.isEmpty()) {
            throw new IllegalArgumentException("Reserva no encontrada");
        }
        
        Reserva reserva = reservaOpt.get();

        // Solo se puede modificar si está en estado "PENDIENTE"
        if (!reserva.getEstadoReserva().equals(EstadoReserva.PENDIENTE)) {
            throw new IllegalArgumentException("Solo se pueden modificar reservas pendientes.");
        }

        // Actualizar los datos permitidos
        if (reservaDTO.getFechaReserva() != null) {
            reserva.setFechaReserva(reservaDTO.getFechaReserva());
        }

        if (reservaDTO.getHoraInicio() != null) {
            reserva.setHoraInicio(reservaDTO.getHoraInicio());
        }
        if (reservaDTO.getHoraFin() != null) {
            reserva.setHoraFin(reservaDTO.getHoraFin());
        }

        // Verificar que la modificación no genere conflictos
        if (!reserva.getEspacio().getTipoEspacio().equals(TipoEspacio.ESCRITORIOCOMPARTIDO)) {
            boolean existeConflicto = reservaRepository.existsByEspacioAndFechaReservaAndHoraInicioLessThanAndHoraFinGreaterThan(
                reserva.getEspacio(), reserva.getFechaReserva(), reserva.getHoraFin(), reserva.getHoraInicio()
            );

            if (existeConflicto) {
                throw new IllegalArgumentException("El espacio ya está reservado en este horario.");
            }
        }

        return reservaRepository.save(reserva);
    }

    @Transactional
    public void eliminarReserva(Long id) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(id);
        if (reservaOpt.isEmpty()) {
            throw new IllegalArgumentException("Reserva no encontrada");
        }

        Reserva reserva = reservaOpt.get();

        // Solo se puede eliminar si está en estado "PENDIENTE"
        if (!reserva.getEstadoReserva().equals(EstadoReserva.PENDIENTE)) {
            throw new IllegalArgumentException("Solo se pueden cancelar reservas pendientes.");
        }

        reservaRepository.delete(reserva);
    }
}
