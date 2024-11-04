package com.example.demo2Prueba.config;

import lombok.Getter;

@Getter
public enum Errores {
    INTERNAL_ERROR("Hubo un error en el servidor, intente mas tarde", 201),
    OTRO_ERROR("HOla",202),
    NOT_FOUND("No se encontro el registro",203);

    private String descripcion;
    private Long status;

    Errores(String descripcion, long status){

        this.descripcion = descripcion;
        this.status= status;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getStatus() {
        return status;
    }
}
