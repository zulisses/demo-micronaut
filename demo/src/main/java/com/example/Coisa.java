package com.example;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity
public record Coisa(
    @GeneratedValue @Id @Nullable Long id, 
    String nome, 
    int qtd) {

    public Coisa (String nome, int qtd) {
        this(null, nome, qtd);
    }
}
