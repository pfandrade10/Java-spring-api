package com.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarroRepositorio extends JpaRepository<Carro, Long> {

    Iterable<Carro> findByTipo(String tipo);
}
