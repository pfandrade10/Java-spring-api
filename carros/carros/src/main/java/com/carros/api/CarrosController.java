package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")

public class CarrosController {

    @Autowired
    private CarroService carroService;

    @GetMapping()
    public ResponseEntity get(){
        return ResponseEntity.ok(carroService.getCarros());
    }

    @GetMapping("/{id}")
    public Optional<Carro> get(@PathVariable("id") Long id){
        return carroService.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity get(@PathVariable("tipo") String tipo){
        return ResponseEntity.ok(carroService.getCarrosByTipo(tipo));
    }

    @PostMapping()
    public String post(@RequestBody Carro carro){
       Carro novoCarro =  carroService.insert(carro);

       return "Carro inserido com sucesso: " + novoCarro.getId();
    }

    @PutMapping("/{id}")
    public String put(@RequestBody Carro carro, @PathVariable("id") Long id){
        Carro carroAlterado = carroService.update(carro, id);

        return "Carro alterado com sucesso! id: "+carroAlterado.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){

        return carroService.delete(id);
    }
}
