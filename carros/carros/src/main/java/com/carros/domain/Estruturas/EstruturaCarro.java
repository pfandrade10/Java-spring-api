package com.carros.domain.Estruturas;

import com.carros.domain.Carro;

import java.util.ArrayList;
import java.util.List;

public class EstruturaCarro extends EstruturaErro{

    private List<Carro> listCarro;

    public EstruturaCarro(){
        listCarro = new ArrayList<>();
    }

    public List<Carro> getListCarro() {
        return listCarro;
    }

    public void setListCarro(List<Carro> listCarro) {
        this.listCarro = listCarro;
    }
}
