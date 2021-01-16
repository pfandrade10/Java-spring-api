package com.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface CarroRepositorio extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);
}
