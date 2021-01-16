package com.carros.domain;

import com.carros.domain.Estruturas.EstruturaCarro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepositorio repo;

    public Iterable<Carro> getCarros(){

           return repo.findAll();
    }

    public Optional<Carro> getCarroById(Long id){
        return repo.findById(id);
    }

    public Iterable<Carro> getCarrosByTipo(String tipo) {
        return repo.findByTipo(tipo);
    }

    public Carro insert(Carro carro) {
       return repo.save(carro);
    }

    public Carro update(Carro carro, Long id) {

        Assert.notNull(id, "Favor passar o carro a ser alterado!");

        Optional<Carro> optional = getCarroById(id);

        if(optional.isPresent()){
            Carro db = optional.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());

            System.out.println("Carro id: "+ db.getId());

            repo.save(db);

            return db;
        }
        else{
            throw new RuntimeException("Não existe registro com identificador "+ id);
        }
    }

    public String delete(Long id){
        try{
            Optional<Carro> optionalCarro = getCarroById(id);

            if(!optionalCarro.isPresent())
                throw new Exception("Erro ao Realizar exclusão! Registro não encontrado");

            repo.deleteById(id);

            return "Registro removido com sucesso! ID: "+ id;
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }
}
