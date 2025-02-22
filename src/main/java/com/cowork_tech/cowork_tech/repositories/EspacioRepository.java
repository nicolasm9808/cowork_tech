package com.cowork_tech.cowork_tech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cowork_tech.cowork_tech.entities.Espacio;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Long>{

    List<Espacio> findByDisponibleIsTrue();

}
