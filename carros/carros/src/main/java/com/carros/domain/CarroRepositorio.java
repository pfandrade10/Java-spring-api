package com.carros.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarroRepositorio extends CrudRepository<Carro, Long> {

    Iterable<Carro> findByTipo(String tipo);
}
