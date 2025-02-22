package com.cowork_tech.cowork_tech.repositories;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cowork_tech.cowork_tech.entities.Espacio;
import com.cowork_tech.cowork_tech.entities.EstadoReserva;
import com.cowork_tech.cowork_tech.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long>{

    List<Reserva> findByEstadoReserva(EstadoReserva TipoEstado);

    boolean existsByEspacioAndFechaReservaAndHoraInicioLessThanAndHoraFinGreaterThan(Espacio espacio, Date fechaReserva, Time horaInicio, Time horaFin);

}
