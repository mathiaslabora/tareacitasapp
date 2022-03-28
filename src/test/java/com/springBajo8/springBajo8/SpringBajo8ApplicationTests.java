package com.springBajo8.springBajo8;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.impl.citasReactivaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class SpringBajo8ApplicationTests {
	@Autowired
	citasReactivaServiceImpl citasReactivaService;

	@Test
	void contextLoads() {
		Mono<String> a = citasReactivaService.buscarUno();
			StepVerifier.create(a).expectNext("Pedro").verifyComplete();

	}
	@Test
	void testFindAll(){
		Flux<citasDTOReactiva> a =citasReactivaService.findAll();
		StepVerifier.create(a).expectComplete();
	}
	@Test
	void testFindById(){
		Mono<citasDTOReactiva> a =citasReactivaService.findById("15");
		StepVerifier.create(a).expectComplete();
	}

}
