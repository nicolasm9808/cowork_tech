package com.cowork_tech.cowork_tech.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cowork_tech.cowork_tech.entities.Espacio;
import com.cowork_tech.cowork_tech.entities.Reserva;
import com.cowork_tech.cowork_tech.entities.ReservaDTO;
import com.cowork_tech.cowork_tech.entities.Usuario;
import com.cowork_tech.cowork_tech.repositories.EspacioRepository;
import com.cowork_tech.cowork_tech.repositories.UsuarioRepository;
import com.cowork_tech.cowork_tech.services.ReservaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/reserva")
public class ReservaController {
    private final ReservaService reservaService;
    private final UsuarioRepository usuarioRepository;
    private final EspacioRepository espacioRepository;

    @PostMapping("{id_usuario}/{id_espacio}")
    public ResponseEntity<Reserva> crearReserva(@PathVariable long id_usuario, @PathVariable long id_espacio, @RequestBody ReservaDTO reservaDTO){
        System.out.println("ReservaDTO recibido: " + reservaDTO);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id_usuario);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        Optional<Espacio> espacioOpt = espacioRepository.findById(id_espacio);
        if (espacioOpt.isEmpty()) {
            throw new IllegalArgumentException("espacio no encontrado");
        }
        Espacio espacio = espacioOpt.get();
        return reservaService.crearReserva(usuario, espacio , reservaDTO);

    }
    
    @GetMapping
    public ResponseEntity<List<Reserva>> reservasDisponibles(){
        return ResponseEntity.ok(reservaService.reservasActivas());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.actualizarReserva(id, reservaDTO));
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Long id){
        reservaService.eliminarReserva(id);
    }

}
