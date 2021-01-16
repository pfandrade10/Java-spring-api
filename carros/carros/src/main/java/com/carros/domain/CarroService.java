package com.carros.domain;

import com.carros.domain.Estruturas.EstruturaCarro;
import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepositorio repo;

    public List<CarroDTO> getCarros(){
        List<Carro> carros = repo.findAll();


        //manualmente fazendo um foreach:
        /* List<CarroDTO> listCarros = new ArrayList<>();
        for (Carro item:
             carros) {
            listCarros.add(new CarroDTO(item));
        }*/

        //Via lambda
        List<CarroDTO> listCarros = carros.stream().map(x -> new CarroDTO(x)).collect(Collectors.toList());

        return listCarros;
    }

    public Optional<Carro> getCarroById(Long id){
        return repo.findById(id);
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        List<Carro> carros = repo.findByTipo(tipo);

        List<CarroDTO> listCarros = carros.stream().map(x -> new CarroDTO(x)).collect(Collectors.toList());

        return listCarros;
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
