package com.cowork_tech.cowork_tech.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="espacio")
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TipoEspacio tipoEspacio;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private long capacidad;
    
    private Boolean disponible = true;

    @OneToMany(mappedBy = "espacio")
    private List<Reserva> reservas;

}
