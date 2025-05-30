package pe.edu.vallegrande.eggs.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.eggs.model.EggProductionModel;
import pe.edu.vallegrande.eggs.service.EggProductionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("/egg-production")
@RequiredArgsConstructor
public class EggProductionRest {

    private final EggProductionService service;

    @GetMapping
    public Flux<EggProductionModel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<EggProductionModel> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public Mono<EggProductionModel> create(@RequestBody EggProductionModel model) {
        return service.create(model);
    }

    @PutMapping("/{id}")
    public Mono<EggProductionModel> update(@PathVariable Integer id, @RequestBody EggProductionModel model) {
        return service.update(id, model);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
