package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class CoisaRepositorioTest {

    @Inject
    CoisaRepositorio coisaRepositorio;

    @Test
    void testCreate() {

        System.out.println(coisaRepositorio.getClass().getName());

        long startCount = coisaRepositorio.count();

        coisaRepositorio.save(new Coisa("Coisa 1", 10));
        
        coisaRepositorio.saveAll(List.of(
            new Coisa("Coisa 2", 20),
            new Coisa("Coisa 3", 30),
            new Coisa("Coisa 4", 40),
            new Coisa("Coisa 5", 50),
            new Coisa("Coisa 6", 60),
            new Coisa("Coisa 7", 70),
            new Coisa("Coisa 8", 80),
            new Coisa("Coisa 9", 90)
        ));

        long endCount = coisaRepositorio.count();
        Assertions.assertEquals(startCount + 9, endCount);

        for(Coisa coisa : coisaRepositorio.findAll()) {
            System.out.println(coisa);
        }
    }
    
    @Test
    void testFindByNome() {
        coisaRepositorio.save(new Coisa("by_name", 10));

        Optional<Coisa> coisa = coisaRepositorio.findByNome("by_name");
        assertTrue(coisa.isPresent());
        assertEquals(10, coisa.get().qtd());

        assertFalse(coisaRepositorio.findByNome("not_found").isPresent());
    }
}
