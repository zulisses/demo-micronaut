package com.example;

import static io.micronaut.context.env.Environment.TEST;

import java.util.List;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
@Requires(notEnv = TEST)
public class PopularDados {
    
    private final CoisaRepositorio coisaRepositorio;

    public PopularDados(CoisaRepositorio coisaRepositorio) {
        this.coisaRepositorio = coisaRepositorio;
    }

    @EventListener 
    @Transactional
    public void init(StartupEvent event) {
        if(coisaRepositorio.count() == 0) {
            coisaRepositorio.saveAll(List.of(
                new Coisa("Coisa 1", 10),
                new Coisa("Coisa 2", 20),
                new Coisa("Coisa 3", 30),
                new Coisa("Coisa 4", 40),
                new Coisa("Coisa 5", 50),
                new Coisa("Coisa 6", 60),
                new Coisa("Coisa 7", 70),
                new Coisa("Coisa 8", 80),
                new Coisa("Coisa 9", 90)
            ));
        }
    }
}
