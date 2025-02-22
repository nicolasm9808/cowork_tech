package com.cowork_tech.cowork_tech.entities;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="espacio_id")
    private Espacio espacio;

    @JsonIgnore
    @ManyToOne  
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Date fechaReserva;

    private Time horaInicio;

    private Time horaFin;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estadoReserva;
}
