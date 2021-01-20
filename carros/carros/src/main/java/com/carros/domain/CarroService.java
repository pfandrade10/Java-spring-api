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

    public Optional<CarroDTO> getCarroById(Long id){

        Optional<Carro> carroDB = repo.findById(id);

        Optional<CarroDTO> carro = carroDB.map(c-> Optional.of(new CarroDTO(c))).orElse(null);

        /*if(carroDB.isPresent()){
            return Optional.of(new CarroDTO(carroDB.get()));
        }
        else{
            return null;
        }*/

        return carro;
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

        Optional<CarroDTO> optional = getCarroById(id);

        if(optional == null){
            return null;
        }

        Optional<Carro> carroDB = optional.map(x -> Optional.of(new Carro(x.getId(), x.getNome(), x.getTipo()))).orElse(null);

        if(carroDB.isPresent()){
            Carro db = carroDB.get();

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
            Optional<CarroDTO> optionalCarro = getCarroById(id);

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
