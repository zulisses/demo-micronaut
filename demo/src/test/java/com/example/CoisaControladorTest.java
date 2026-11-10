package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.micronaut.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest 
public class CoisaControladorTest {

    @Client("/coisa")
    interface CoisaClient {
        @Get("/{id}")
        Optional<Coisa> findById(Long id);

        @Get("/all")
        List<Coisa> findAll();

        @Post("/create/{nome}/{qtd}")
        HttpResponse<Coisa> create(String nome, int qtd);
    }

    @Test 
    void testCreate(CoisaClient coisaClient) {
        List<Coisa> antes = coisaClient.findAll();

        Coisa coisa = coisaClient.create("Teste", 1).body();
        assertNotNull(coisa);
        assertNotNull(coisa.id());

        List<Coisa> depois = coisaClient.findAll();
        assertEquals(antes.size() + 1, depois.size());
        assertTrue(depois.contains(coisa));
    }

    @Test
    void testCreateFail(CoisaClient coisaClient) {
        HttpClientResponseException e = assertThrows(HttpClientResponseException.class, () -> 
            coisaClient.create("Teste", -100));
        
        assertEquals(BAD_REQUEST, e.getStatus());
    }

}
