package com.springBajo8.springBajo8.web;


import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
public class citasReactivaResource {

    @Autowired
    private IcitasReactivaService icitasReactivaService;

    @PostMapping(value = "/citasReactivas")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<citasDTOReactiva> save(@RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.save(citasDTOReactiva);
    }

    @DeleteMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> delete(@PathVariable("id") String id) {
        return this.icitasReactivaService.delete(id)
                .flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> update(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping("/citasReactivas/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> findAllByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.findByIdPaciente(idPaciente);
    }

    @GetMapping(value = "/citasReactivas")
    private Flux<citasDTOReactiva> findAll() {
        return this.icitasReactivaService.findAll();
    }

    @PutMapping("/cancelarCita/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> cancelCitaByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.cancelCita(idPaciente);
    }

    @PutMapping("/agendarCita/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> agendarCitaByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.agendCita(idPaciente);
    }
    @GetMapping("/citaPorFecha/{hora}/{fecha}")
    private Flux<citasDTOReactiva> consultaCitaPorFecha(@PathVariable("hora") String hora, @PathVariable("fecha") String fecha) {
        LocalDate fechaN = LocalDate.parse(fecha);
        return this.icitasReactivaService.consultaCitaPorFechaYHora(fechaN, hora);
    }
    @GetMapping(value = "/medicoCita/{idPaciente}")
    private Flux<citasDTOReactiva>consultaMedico(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.consultaMedico(idPaciente);
    }

    @GetMapping(value = "/padecimientosytratamientos/{idPaciente}")
    private Flux<citasDTOReactiva>consultaPYT(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.consultaPYT(idPaciente);
    }

}
