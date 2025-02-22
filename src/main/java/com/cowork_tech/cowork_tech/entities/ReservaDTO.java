package com.cowork_tech.cowork_tech.entities;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ReservaDTO {

    private Date fechaReserva;

    private Time horaInicio;

    private Time horaFin;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estadoReserva;
}
