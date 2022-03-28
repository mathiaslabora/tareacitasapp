package com.springBajo8.springBajo8.service.impl;

//import com.yoandypv.reactivestack.messages.domain.Message;
//import com.yoandypv.reactivestack.messages.repository.MessageRepository;
//import com.yoandypv.reactivestack.messages.service.MessageService;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;

@Service
public class citasReactivaServiceImpl implements IcitasReactivaService {

    @Autowired
    private IcitasReactivaRepository IcitasReactivaRepository;

    @Override
    public Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.save(citasDTOReactiva);
    }

    @Override
    public Mono<citasDTOReactiva> delete(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));

    }

    @Override
    public Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> findByIdPaciente(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }


    @Override
    public Flux<citasDTOReactiva> findAll() {
        return this.IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<citasDTOReactiva> findById(String id) {
        return this.IcitasReactivaRepository.findById(id);
    }

    @Override
    public Flux<citasDTOReactiva> cancelCita(String id) {
        return this.IcitasReactivaRepository.findByIdPaciente(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva1.setEstadoReservaCita("Cancelado");
                    return save(citasDTOReactiva1);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> agendCita(String id) {
        return this.IcitasReactivaRepository.findByIdPaciente(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva1.setEstadoReservaCita("Agendado");
                    return save(citasDTOReactiva1);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> consultaCitaPorFechaYHora(LocalDate fecha, String hora) {
        return this.IcitasReactivaRepository.findByFechaReservaCita(fecha)
                .filter(x -> x.getHoraReservaCita().equals(hora))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Flux<citasDTOReactiva> consultaMedico(String id) {
        return this.IcitasReactivaRepository.findByIdPaciente(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva a = new citasDTOReactiva();

                    a.setNombreMedico(citasDTOReactiva1.getNombreMedico());
                    a.setApellidosMedico(citasDTOReactiva1.getApellidosMedico());
                            return Flux.just(a);
                        }
                )
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> consultaPYT(String id) {
        return this.IcitasReactivaRepository.findByIdPaciente(id)
                .flatMap(citasDTOReactiva1 -> {
                            citasDTOReactiva a = new citasDTOReactiva();

                            a.setPadecimientos(citasDTOReactiva1.getPadecimientos());
                            a.setTratamientos(citasDTOReactiva1.getTratamientos());
                            return Flux.just(a);
                        }
                )
                .switchIfEmpty(Mono.empty());
    }


}
