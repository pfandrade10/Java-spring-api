package com.carros.domain.Estruturas;

public class EstruturaErro {

    private Boolean isError;

    private String descricaoErro;

    public EstruturaErro(){

        isError = false;
        descricaoErro = "";
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getDescricaoErro() {
        return descricaoErro;
    }

    public void setDescricaoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }
}
