package com.cowork_tech.cowork_tech.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.cowork_tech.cowork_tech.entities.Espacio;
import com.cowork_tech.cowork_tech.services.EspacioService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@RestController
@RequestMapping("/api/espacio")
public class EspacioController {

    private final EspacioService espacioService;

    @PostMapping
    public ResponseEntity<Espacio> crearEspacio(@RequestBody Espacio espacio){
        return ResponseEntity.status(HttpStatus.CREATED).body(espacioService.crearEspacio(espacio));
    }
    
    @GetMapping
    public ResponseEntity<List<Espacio>> espaciosDisponibles(){
        return ResponseEntity.ok(espacioService.espaciosDisponibles());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Espacio> actualizarEspacio(@PathVariable Long id, @RequestBody Espacio espacio){
        return ResponseEntity.status(HttpStatus.OK).body(espacioService.actualizarEspacio(id, espacio));
    }

    @DeleteMapping("/{id}")
    public void eliminarEspacio(@PathVariable Long id){
        espacioService.eliminarEspacio(id);
    }

}
