package pe.edu.vallegrande.eggs.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import pe.edu.vallegrande.eggs.model.EggProductionModel;
import pe.edu.vallegrande.eggs.rest.EggProductionRest;
import pe.edu.vallegrande.eggs.service.EggProductionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@WebFluxTest(EggProductionRest.class)
public class EggProductionRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EggProductionService service;

    @Test
    public void testGetAll() {
        EggProductionModel model = createSampleModel(1);

        Mockito.when(service.getAll()).thenReturn(Flux.just(model));

        webTestClient.get().uri("/egg-production")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EggProductionModel.class)
                .hasSize(1)
                .contains(model);
    }

    @Test
    public void testGetById() {
        EggProductionModel model = createSampleModel(1);

        Mockito.when(service.getById(1)).thenReturn(Mono.just(model));

        webTestClient.get().uri("/egg-production/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EggProductionModel.class)
                .isEqualTo(model);
    }

    @Test
    public void testCreate() {
        EggProductionModel inputModel = createSampleModel(null); // sin id para creación
        EggProductionModel savedModel = createSampleModel(1);    // con id asignado

        Mockito.when(service.create(inputModel)).thenReturn(Mono.just(savedModel));

        webTestClient.post().uri("/egg-production")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inputModel)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EggProductionModel.class)
                .isEqualTo(savedModel);
    }

    @Test
    public void testUpdate() {
        EggProductionModel inputModel = createSampleModel(null);
        EggProductionModel updatedModel = createSampleModel(1);

        Mockito.when(service.update(1, inputModel)).thenReturn(Mono.just(updatedModel));

        webTestClient.put().uri("/egg-production/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inputModel)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EggProductionModel.class)
                .isEqualTo(updatedModel);
    }

    @Test
    public void testDelete() {
        Mockito.when(service.delete(1)).thenReturn(Mono.empty());

        webTestClient.delete().uri("/egg-production/1")
                .exchange()
                .expectStatus().isOk();
    }

    // Helper para crear un modelo de huevo con valores por defecto
    private EggProductionModel createSampleModel(Integer id) {
        EggProductionModel model = new EggProductionModel();
        model.setId(id);
        model.setQuantityEggs(100);
        model.setEggsKilo(12);
        model.setPriceKilo(new BigDecimal("15.5"));
        model.setRegistrationDate(LocalDate.now());
        return model;
    }
}
