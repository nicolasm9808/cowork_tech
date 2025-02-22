package com.cowork_tech.cowork_tech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cowork_tech.cowork_tech.entities.Espacio;
import com.cowork_tech.cowork_tech.repositories.EspacioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EspacioService {

    private final EspacioRepository espacioRepository;

    public Espacio crearEspacio(Espacio espacio){
        return espacioRepository.save(espacio);
    }

    public List<Espacio> espaciosDisponibles(){
        return espacioRepository.findByDisponibleIsTrue();
    }

    public Espacio actualizarEspacio(Long id,Espacio espacio){
        Optional<Espacio> espacioOpt = espacioRepository.findById(id);
        if (espacioOpt.isEmpty()){
            throw new IllegalArgumentException("Espacio no encontrado");
        }

        Espacio espacio1 = espacioOpt.get();
        System.out.println(espacio1);

        espacio1.setNombre(espacio.getNombre());
        espacio1.setTipoEspacio(espacio.getTipoEspacio());
        espacio1.setCapacidad(espacio.getCapacidad());
        espacio1.setDisponible(espacio.getDisponible());
        System.out.println(espacio1);

        return espacioRepository.save(espacio1);
    }

    public void eliminarEspacio(Long id) {
        Optional<Espacio> espacioOpt = espacioRepository.findById(id);
        if (espacioOpt.isEmpty()){
            throw new IllegalArgumentException("Espacio no encontrado");
        }
        Espacio espacio = espacioOpt.get();
        espacioRepository.delete(espacio);
    }

    

}
