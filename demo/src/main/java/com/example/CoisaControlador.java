package com.example;

import java.util.List;
import java.util.Optional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.Positive;

@Controller("/coisa")
@Validated
@ExecuteOn(TaskExecutors.IO)
public class CoisaControlador {

    private final CoisaRepositorio coisaRepositorio;

    public CoisaControlador(CoisaRepositorio coisaRepositorio) {
        this.coisaRepositorio = coisaRepositorio;
    }

    @Get("/{id}")
    public Optional<Coisa> findById(@PathVariable Long id) {
        return coisaRepositorio.findById(id);
    }

    @Get("/all")
    List<Coisa> findAll() {
        return coisaRepositorio.findAll();
    }

    @Post("/create/{nome}/{qtd}")
    HttpResponse<Coisa> create(@PathVariable String nome, @Positive @PathVariable int qtd) {
        Coisa coisa = coisaRepositorio.save(new Coisa(nome, qtd));
        return HttpResponse.created(coisa);
    }
}
